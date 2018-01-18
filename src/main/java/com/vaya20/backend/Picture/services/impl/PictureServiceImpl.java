package com.vaya20.backend.Picture.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya20.backend.Picture.domain.Picture;
import com.vaya20.backend.Picture.domain.PictureList;
import com.vaya20.backend.Picture.repositories.PictureListRepository;
import com.vaya20.backend.Picture.repositories.PictureRepository;
import com.vaya20.backend.Picture.services.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	
	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	PictureListRepository pictureListRepository;

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
	public List<Picture> fetchPostsByCategoryId(long categoryId) {
		return pictureRepository.findAllPicturesWhereDeleteYNIsNAndCategoryIdMatches(categoryId);
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
