package com.vaya20.backend.Picture.services;

import java.util.List;

import com.vaya20.backend.Picture.domain.Picture;
import com.vaya20.backend.Picture.domain.PictureList;

public interface PictureService {
	public void updatePost(long id);
	public void delete(long id);
	public List<Picture> fetchPostsByCategoryId(long categoryId);
	public void save(Picture resource);
	public Picture findOne(long id);
}
