package com.vaya20.backend.Sermon.services;

import java.util.List;

import com.vaya20.backend.Sermon.domain.SermonPost;

public interface SermonService {
	public void updatePost(long id);
	public void delete(long id);
	public List<SermonPost> fetchPostsById();
	public void save(SermonPost resource);
	public SermonPost findOne(long id);
}
