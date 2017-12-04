package com.vaya20.backend.YAColumn.controllers;

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
import com.vaya20.backend.YAColumn.domain.YAColumn;
import com.vaya20.backend.YAColumn.services.YAColumnService;

@Controller
public class YAColumnController {
	@Autowired
	YAColumnService yaColumnService;
	
	public YAColumnController(YAColumnService yaColumnService) {
		this.yaColumnService = yaColumnService;
	}
	
	@RequestMapping(value="/yacolumn-list", method=RequestMethod.GET)
	public ResponseEntity<?> sermonList() {
		List<YAColumn> yaColumns = yaColumnService.fetchPostsById();
		return new ResponseEntity<List<YAColumn>>(yaColumns,HttpStatus.OK);
	}
	
	@RequestMapping(value="/yacolumn-readpost/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> sermonReadPost(@PathVariable("id") long id) {
		YAColumn yaColumn = yaColumnService.findOne(id);
		return new ResponseEntity<YAColumn>(yaColumn, HttpStatus.OK);
	}
	
	@RequestMapping(value="/yacolumn-create", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void sermonCreate(@RequestBody YAColumn resource) {
		resource.setDeleteYN('N');
		yaColumnService.save(resource);
	}
	
	@RequestMapping(value="/yacolumn-delete/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deletePost(@PathVariable("id") long id) {
		yaColumnService.delete(id);
	}
	
	@RequestMapping(value="/yacolumn-edit/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updatePost(@PathVariable("id") long id, @RequestBody SermonPost resource) {
		YAColumn post = yaColumnService.findOne(id);
		post.setTitle(resource.getTitle());
		post.setAuthor(resource.getAuthor());
		post.setDate(resource.getDate());
		post.setMainVerse(resource.getMainVerse());
		post.setBody(resource.getBody());
		post.setDeleteYN('N');
		yaColumnService.save(post);
	}
}
