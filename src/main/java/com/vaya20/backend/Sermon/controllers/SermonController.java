package com.vaya20.backend.Sermon.controllers;

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

import com.vaya20.backend.Sermon.domain.SermonPost;
import com.vaya20.backend.Sermon.services.SermonService;

@Controller
public class SermonController {
	
	@Autowired
	SermonService sermonService;
	
	public SermonController(SermonService sermonService) {
		this.sermonService = sermonService;
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
	
	@RequestMapping(value="/sermon-create", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void sermonCreate(@RequestBody SermonPost resource) {
		resource.setDeleteYN('N');
		sermonService.save(resource);
	}
}
