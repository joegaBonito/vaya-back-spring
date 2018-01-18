package com.vaya20.backend.Picture.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya20.backend.Picture.domain.PictureList;
import com.vaya20.backend.Picture.repositories.PictureListRepository;
import com.vaya20.backend.Picture.services.PictureListService;

@Service
public class PictureListServiceImpl implements PictureListService {

	@Autowired
	PictureListRepository pictureListRepository;
	
	@Override
	public void updatePost(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		PictureList pictureList = pictureListRepository.findOne(id);
		pictureList.setDeleteYN('Y');
		save(pictureList);
	}

	@Override
	public List<PictureList> fetchPostsById() {
		return pictureListRepository.findAllPictureListsWhereDeleteYNIsN();
	}

	@Override
	public void save(PictureList resource) {
		pictureListRepository.save(resource);
	}

	@Override
	public PictureList findOne(long id) {
		return pictureListRepository.findOne(id);
	}
}
