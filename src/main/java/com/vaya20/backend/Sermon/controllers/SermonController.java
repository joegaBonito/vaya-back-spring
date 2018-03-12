package com.vaya20.backend.Sermon.controllers;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.google.common.io.ByteStreams;
import com.vaya20.backend.Sermon.domain.SermonPost;
import com.vaya20.backend.Sermon.services.SermonService;
import com.vaya20.backend.Sermon.services.impl.SermonStorageService;

@Controller
@RequestMapping("/api")
public class SermonController {
	
	@Autowired
	SermonService sermonService;
	
	@Autowired
	SermonStorageService sermonStorageService;
	
	public SermonController(SermonService sermonService, 
			SermonStorageService sermonStorageService) {
		this.sermonService = sermonService;
		this.sermonStorageService = sermonStorageService;
	}
	
	@RequestMapping(value="/sermon-list", method=RequestMethod.GET)
	public ResponseEntity<?> sermonList() {
		List<SermonPost> sermonPosts = sermonService.fetchPostsById();
		return new ResponseEntity<List<SermonPost>>(sermonPosts,HttpStatus.OK);
	}
	
	@RequestMapping(value="/sermon-readpost/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> sermonReadPost(@PathVariable("id") long id) {
		SermonPost sermonPost = sermonService.findOne(id);
		return new ResponseEntity<SermonPost>(sermonPost, HttpStatus.OK);
	}
	
	/*
	 * Calls the file to web from the file system.
	 */
	@RequestMapping(value="/sermon-file", method=RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> readFile(@RequestParam("filename") String filename) throws IOException {
		Resource resource = sermonStorageService.loadAsResource(filename);
		InputStream stream = resource.getInputStream();
		byte[] content =  ByteStreams.toByteArray(stream);
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/sermon-create", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void sermonCreate(@RequestParam(value="file") MultipartFile file,
			@RequestParam(value="title") String title,
			@RequestParam(value="author") String author,
			@RequestParam(value="mainVerse") String mainVerse,
			@RequestParam(value="date") String date,
			@RequestParam(value="body") String body,
			@RequestParam(value="fileName") String fileName) throws IOException {
		SermonPost sermonPost = new SermonPost();
		sermonStorageService.store(file); //<--Add to the file system.
		sermonPost.setTitle(title);
		sermonPost.setAuthor(author);
		sermonPost.setMainVerse(mainVerse);
		sermonPost.setDate(date);
		sermonPost.setBody(body);
		sermonPost.setFileName(fileName);
		sermonPost.setDeleteYN('N');
		sermonService.save(sermonPost);
	}
	
	@RequestMapping(value="/sermon-delete/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deletePost(@PathVariable("id") long id) {
		sermonService.delete(id);
	}
	
	@RequestMapping(value="/sermon-edit/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updatePost(@PathVariable("id") long id, 
			@RequestParam(value="file") MultipartFile file,
			@RequestParam(value="title") String title,
			@RequestParam(value="author") String author,
			@RequestParam(value="mainVerse") String mainVerse,
			@RequestParam(value="date") String date,
			@RequestParam(value="body") String body,
			@RequestParam(value="fileName") String fileName) throws IOException {
		SermonPost sermonPost = sermonService.findOne(id);
		sermonStorageService.store(file); //<--Add to the file system.
		sermonPost.setTitle(title);
		sermonPost.setAuthor(author);
		sermonPost.setMainVerse(mainVerse);
		sermonPost.setDate(date);
		sermonPost.setBody(body);
		sermonPost.setFileName(fileName);
		sermonPost.setDeleteYN('N');
		sermonService.save(sermonPost);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
