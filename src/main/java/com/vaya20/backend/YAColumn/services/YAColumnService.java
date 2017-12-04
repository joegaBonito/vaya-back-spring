package com.vaya20.backend.YAColumn.services;

import java.util.List;

import com.vaya20.backend.YAColumn.domain.YAColumn;

public interface YAColumnService {
	public void updatePost(long id);
	public void delete(long id);
	public List<YAColumn> fetchPostsById();
	public void save(YAColumn resource);
	public YAColumn findOne(long id);
}
