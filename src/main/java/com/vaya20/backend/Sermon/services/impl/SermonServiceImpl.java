package com.vaya20.backend.Sermon.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya20.backend.Sermon.domain.SermonPost;
import com.vaya20.backend.Sermon.repositories.SermonRepository;
import com.vaya20.backend.Sermon.services.SermonService;

@Service
public class SermonServiceImpl implements SermonService {

	@Autowired
	SermonRepository sermonRepository;

	@Override
	public void updatePost(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		SermonPost sermonPost = sermonRepository.findOne(id);
		sermonPost.setDeleteYN('Y');
		save(sermonPost);
	}

	@Override
	public List<SermonPost> fetchPostsById() {
		return sermonRepository.findAllSermonPostsWhereDeleteYNIsN();
	}

	@Override
	public void save(SermonPost resource) {
		sermonRepository.save(resource);
	}

	@Override
	public SermonPost findOne(long id) {
		return sermonRepository.findOne(id);
	}

}
