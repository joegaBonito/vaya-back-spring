package com.vaya20.backend.News.controllers;

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

import com.vaya20.backend.News.domain.News;
import com.vaya20.backend.News.services.NewsService;
import com.vaya20.backend.Picture.domain.Picture;
import com.vaya20.backend.Sermon.domain.SermonPost;

@Controller
public class NewsController {
	
	@Autowired
	NewsService newsService;
	
	NewsController(NewsService newsService) {
		this.newsService = newsService;
	}
	
	@RequestMapping(value="/news-read/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getNews(@PathVariable("id") long id){
		return new ResponseEntity<News>(newsService.getNews(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/news-edit/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updatePost(@PathVariable("id") long id, @RequestBody News resource) {
		News post = newsService.getNews(id);
		post.setTitle1(resource.getTitle1());
		post.setTitle2(resource.getTitle2());
		post.setTitle3(resource.getTitle3());
		post.setTitle4(resource.getTitle4());
		post.setTitle5(resource.getTitle5());
		
		post.setDescription1(resource.getDescription1());
		post.setDescription2(resource.getDescription2());
		post.setDescription3(resource.getDescription3());
		post.setDescription4(resource.getDescription4());
		post.setDescription5(resource.getDescription5());
		
		post.setDeleteYN('N');
		newsService.save(post);
	}

}
