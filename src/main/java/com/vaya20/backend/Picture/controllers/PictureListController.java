package com.vaya20.backend.Picture.controllers;

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

import com.vaya20.backend.Picture.domain.PictureList;
import com.vaya20.backend.Picture.services.PictureListService;

@Controller
public class PictureListController {
	@Autowired
	PictureListService pictureListService;
	
	public PictureListController(PictureListService pictureListService) {
		this.pictureListService = pictureListService;
	}
	

	@RequestMapping(value="/pictureList-list", method=RequestMethod.GET)
	public ResponseEntity<?> pictureListList() {
		List<PictureList> pictureLists = pictureListService.fetchPostsById();
		return new ResponseEntity<List<PictureList>>(pictureLists,HttpStatus.OK);
	}
	
	@RequestMapping(value="/pictureList-read/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> pictureListRead(@PathVariable("id") long id) {
		PictureList pictureList = pictureListService.findOne(id);
		return new ResponseEntity<PictureList>(pictureList, HttpStatus.OK);
	}
	/*
	 * Displays image to web from the database blob.
	 */
	@RequestMapping(value="/pictureList-file/{id}", method=RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<?> pictureListReadFile(@PathVariable("id") long id) {
		byte[] imageContent =  pictureListService.findOne(id).getFile();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent,headers, HttpStatus.OK);
	}
	
	@RequestMapping(value=("/pictureList-create"), method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void pictureListCreate(@RequestParam(value="file") MultipartFile file,
					@RequestParam(value="title") String title,
					@RequestParam(value="year") String year,
					@RequestParam(value="originalFileName") String originalFileName
					) throws IOException {
//		This was used for the file system.
//		storageService.store(file); 
		PictureList pictureList = new PictureList();
		pictureList.setFile(file.getBytes());
		pictureList.setTitle(title);
		pictureList.setYear(year);
		pictureList.setOriginalFileName(originalFileName);
		pictureList.setDeleteYN('N');
		pictureListService.save(pictureList);
	}
	
	@RequestMapping(value="/pictureList-delete/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void pictureDelete(@PathVariable("id") long id) {
		pictureListService.delete(id);
	}
	
	@RequestMapping(value="/pictureList-edit/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void pictureListUpdate(@PathVariable("id") long id, 
			@RequestParam(value="file") MultipartFile file,
			@RequestParam(value="title") String title,
			@RequestParam(value="year") String year,
			@RequestParam(value="originalFileName") String originalFileName) throws IOException {
//		This was used for the file system.
//		storageService.store(file); 
		PictureList pictureList = pictureListService.findOne(id);
		pictureList.setTitle(title);
		pictureList.setYear(year);
		pictureList.setDeleteYN('N');
		pictureList.setFile(file.getBytes());
		pictureList.setOriginalFileName(originalFileName);
		pictureListService.save(pictureList);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
