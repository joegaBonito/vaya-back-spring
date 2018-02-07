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

import com.vaya20.backend.Picture.domain.Picture;
import com.vaya20.backend.Picture.domain.PictureList;
import com.vaya20.backend.Picture.services.PictureListService;
import com.vaya20.backend.Picture.services.PictureService;
import com.vaya20.backend.storage.StorageService;

@Controller
@RequestMapping("/api")
public class PictureController  {
	
	@Autowired
	private final StorageService storageService;
	
	@Autowired
	PictureService pictureService;
	
	@Autowired
	PictureListService pictureListService;
	
	public PictureController(PictureService pictureService, PictureListService pictureListService,StorageService storageService) {
		this.pictureService = pictureService;
		this.pictureListService =pictureListService;
		this.storageService = storageService;
	}
	
	@RequestMapping(value="/picture-list/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> list(@PathVariable("id") long categoryId) {
		List<Picture> pictures = pictureService.fetchPostsByCategoryId(categoryId);
		return new ResponseEntity<List<Picture>>(pictures,HttpStatus.OK);
	}
	
	@RequestMapping(value="/picture-read/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> read(@PathVariable("id") long id) {
		Picture picture = pictureService.findOne(id);
		return new ResponseEntity<Picture>(picture, HttpStatus.OK);
	}
	/*
	 * Displays image to web from the database blob.
	 */
	@RequestMapping(value="/picture-file/{id}", method=RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<?> readFile(@PathVariable("id") long id) {
		byte[] imageContent =  pictureService.findOne(id).getFile();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent,headers, HttpStatus.OK);
	}
	
//	@GetMapping("/picture-file/{filename:.+}")
//	@ResponseBody
//	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//		Resource file = storageService.loadAsResource(filename);
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	}
	
	@RequestMapping(value=("/picture-create"), method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestParam(value="file") MultipartFile file,
					@RequestParam(value="title") String title,
					@RequestParam(value="author") String author,
					@RequestParam(value="date") String date,
					@RequestParam(value="body") String body,
					@RequestParam(value="originalFileName") String originalFileName,
					@RequestParam(value="categoryId") Long categoryId
					) throws IOException {
//		This was used for the file system.
//		storageService.store(file); 
		Picture picture = new Picture();
		picture.setTitle(title);
		picture.setAuthor(author);
		picture.setDate(date);
		picture.setBody(body);
		picture.setDeleteYN('N');
		picture.setFile(file.getBytes());
		picture.setOriginalFileName(originalFileName);
		picture.setPictureList(pictureListService.findOne(categoryId));
		pictureService.save(picture);
	}
	
	@RequestMapping(value="/picture-delete/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") long id) {
		pictureService.delete(id);
	}
	
	@RequestMapping(value="/picture-edit/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") long id, 
			@RequestParam(value="file") MultipartFile file,
			@RequestParam(value="title") String title,
			@RequestParam(value="author") String author,
			@RequestParam(value="date") String date,
			@RequestParam(value="body") String body,
			@RequestParam(value="originalFileName") String originalFileName) throws IOException {
//		This was used for the file system.
//		storageService.store(file); 
		Picture picture = pictureService.findOne(id);
		picture.setTitle(title);
		picture.setAuthor(author);
		picture.setDate(date);
		picture.setBody(body);
		picture.setDeleteYN('N');
		picture.setFile(file.getBytes());
		picture.setOriginalFileName(originalFileName);
		pictureService.save(picture);
	}
	
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
