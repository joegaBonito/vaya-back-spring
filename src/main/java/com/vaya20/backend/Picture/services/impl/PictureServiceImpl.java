package com.vaya20.backend.Picture.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya20.backend.Picture.domain.Picture;
import com.vaya20.backend.Picture.repositories.PictureRepository;
import com.vaya20.backend.Picture.services.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	
	@Autowired
	PictureRepository pictureRepository;

	@Override
	public void updatePost(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		Picture picture = pictureRepository.findOne(id);
		picture.setDeleteYN('Y');
		save(picture);
	}

	@Override
	public List<Picture> fetchPostsById() {
		return pictureRepository.findAllPicturesWhereDeleteYNIsN();
	}

	@Override
	public void save(Picture resource) {
		pictureRepository.save(resource);
		
	}

	@Override
	public Picture findOne(long id) {
		return pictureRepository.findOne(id);
	}

}
