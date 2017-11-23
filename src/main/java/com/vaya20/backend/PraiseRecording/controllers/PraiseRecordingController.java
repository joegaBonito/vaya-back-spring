package com.vaya20.backend.PraiseRecording.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vaya20.backend.PraiseRecording.domain.PraiseRecording;
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
	
	@RequestMapping(value="/praiserecording-create", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody PraiseRecording resource) {
		resource.setDeleteYN('N');
		praiseRecordingService.save(resource);
	}
	
	@RequestMapping(value="/praiserecording-delete/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") long id) {
		praiseRecordingService.delete(id);
	}
	
	@RequestMapping(value="/praiserecording-edit/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") long id, @RequestBody PraiseRecording resource) {
		PraiseRecording post = praiseRecordingService.findOne(id);
		post.setTitle(resource.getTitle());
		post.setAuthor(resource.getAuthor());
		post.setDate(resource.getDate());
		post.setFile(resource.getFile());
		post.setBody(resource.getBody());
		post.setDeleteYN('N');
		praiseRecordingService.save(post);
	}
}
