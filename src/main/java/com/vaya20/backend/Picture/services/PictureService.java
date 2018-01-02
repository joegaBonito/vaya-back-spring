package com.vaya20.backend.Picture.services;

import java.util.List;

import com.vaya20.backend.Picture.domain.Picture;

public interface PictureService {
	public void updatePost(long id);
	public void delete(long id);
	public List<Picture> fetchPostsById();
	public void save(Picture resource);
	public Picture findOne(long id);
}
