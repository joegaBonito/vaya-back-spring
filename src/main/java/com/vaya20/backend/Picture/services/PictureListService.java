package com.vaya20.backend.Picture.services;

import java.util.List;

import com.vaya20.backend.Picture.domain.PictureList;

public interface PictureListService {
	public void updatePost(long id);
	public void delete(long id);
	public List<PictureList> fetchPostsById();
	public void save(PictureList resource);
	public PictureList findOne(long id);
}
