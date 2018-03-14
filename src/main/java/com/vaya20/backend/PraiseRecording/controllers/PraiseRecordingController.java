package com.vaya20.backend.PraiseRecording.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.google.common.io.ByteStreams;
import com.vaya20.backend.PraiseRecording.domain.PraiseRecording;
import com.vaya20.backend.PraiseRecording.services.PraiseRecordingService;
import com.vaya20.backend.PraiseRecording.services.impl.PraiseRecordingStorageService;

@Controller
@RequestMapping("/api")
public class PraiseRecordingController {
	
	@Autowired
	PraiseRecordingService praiseRecordingService;
	
	@Autowired
	PraiseRecordingStorageService praiseRecordingStorageService;
	
	public PraiseRecordingController(PraiseRecordingService praiseRecordingService, PraiseRecordingStorageService praiseRecordingStorageService) {
		this.praiseRecordingService = praiseRecordingService;
		this.praiseRecordingStorageService = praiseRecordingStorageService;
	}
	
	@RequestMapping(value="/praiserecording-list", method=RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<PraiseRecording> praiseRecordings = praiseRecordingService.fetchPostsById();
		return new ResponseEntity<List<PraiseRecording>>(praiseRecordings,HttpStatus.OK);
	}
	
	@RequestMapping(value="/praiserecording-read/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> read(@PathVariable("id") long id) {
		PraiseRecording praiseRecording = praiseRecordingService.findOne(id);
		return new ResponseEntity<PraiseRecording>(praiseRecording, HttpStatus.OK);
	}
	/*
	 * Calls the file to web from the file system.
	 */
	@RequestMapping(value="/praiserecording-file", method=RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<?> readFile(@RequestParam("filename") String filename) throws IOException {
		Resource resource = praiseRecordingStorageService.loadAsResource(filename);
		InputStream stream = resource.getInputStream();
		byte[] content =  ByteStreams.toByteArray(stream);
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value=("/praiserecording-create"), method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestParam(value="file") MultipartFile file,
					@RequestParam(value="title") String title,
					@RequestParam(value="author") String author,
					@RequestParam(value="date") String date,
					@RequestParam(value="body") String body,
					@RequestParam(value="fileName") String fileName
					) throws IOException {
		PraiseRecording praiseRecording = new PraiseRecording();
		praiseRecordingStorageService.store(file); //<--Add to the file system.
		praiseRecording.setTitle(title);
		praiseRecording.setAuthor(author);
		praiseRecording.setDate(date);
		praiseRecording.setBody(body);
		praiseRecording.setFileName(fileName);
		praiseRecording.setDeleteYN('N');
		praiseRecordingService.save(praiseRecording);
	}
	
	@RequestMapping(value="/praiserecording-delete/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") long id) {
		praiseRecordingService.delete(id);
	}
	
	@RequestMapping(value="/praiserecording-edit/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") long id, 
			@RequestParam(value="file") MultipartFile file,
			@RequestParam(value="title") String title,
			@RequestParam(value="author") String author,
			@RequestParam(value="date") String date,
			@RequestParam(value="body") String body,
			@RequestParam(value="fileName") String fileName) throws IOException {
		PraiseRecording praiseRecording = praiseRecordingService.findOne(id);
		praiseRecordingStorageService.store(file); //<--Add to the file system.
		praiseRecording.setTitle(title);
		praiseRecording.setAuthor(author);
		praiseRecording.setDate(date);
		praiseRecording.setBody(body);
		praiseRecording.setFileName(fileName);
		praiseRecording.setDeleteYN('N');
		praiseRecordingService.save(praiseRecording);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
