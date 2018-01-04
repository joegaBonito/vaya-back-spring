package com.vaya20.backend.PraiseRecording.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.vaya20.backend.PraiseRecording.domain.PraiseRecording;
import com.vaya20.backend.PraiseRecording.domain.PraiseRecordingFile;
import com.vaya20.backend.PraiseRecording.services.PraiseRecordingService;

@Controller
public class PraiseRecordingController {
	
	@Autowired
	PraiseRecordingService praiseRecordingService;
	
	public PraiseRecordingController(PraiseRecordingService praiseRecordingService) {
		this.praiseRecordingService = praiseRecordingService;
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
	 * Displays image to web from the database blob.
	 */
	@RequestMapping(value="/praiserecording-file/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<?> readFile(@PathVariable("id") long id) {
		byte[] imageContent =  praiseRecordingService.findOne(id).getPraiseRecordingFile().getFile();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(imageContent,headers, HttpStatus.OK);
	}
	
	@RequestMapping(value=("/praiserecording-create"), method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestParam(value="file") MultipartFile file,
					@RequestParam(value="title") String title,
					@RequestParam(value="author") String author,
					@RequestParam(value="date") String date,
					@RequestParam(value="body") String body
					) throws IOException {
		PraiseRecording praiseRecording = new PraiseRecording();
		PraiseRecordingFile praiseRecordingFile = new PraiseRecordingFile();
		praiseRecordingFile.setFile(file.getBytes());
		praiseRecording.setPraiseRecordingFile(praiseRecordingFile);
		praiseRecording.setTitle(title);
		praiseRecording.setAuthor(author);
		praiseRecording.setDate(date);
		praiseRecording.setBody(body);
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
			@RequestParam(value="body") String body) throws IOException {
		PraiseRecording praiseRecording = praiseRecordingService.findOne(id);
		PraiseRecordingFile praiseRecordingFile = praiseRecordingService.findOne(id).getPraiseRecordingFile();
		praiseRecordingFile.setFile(file.getBytes());
		praiseRecording.setPraiseRecordingFile(praiseRecordingFile);
		praiseRecording.setTitle(title);
		praiseRecording.setAuthor(author);
		praiseRecording.setDate(date);
		praiseRecording.setBody(body);
		praiseRecording.setDeleteYN('N');
		praiseRecordingService.save(praiseRecording);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
