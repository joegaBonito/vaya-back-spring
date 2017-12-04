package com.vaya20.backend.YAColumn.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya20.backend.YAColumn.domain.YAColumn;
import com.vaya20.backend.YAColumn.repositories.YAColumnRepository;
import com.vaya20.backend.YAColumn.services.YAColumnService;

@Service
public class YAColumnServiceImpl implements YAColumnService {

	@Autowired
	YAColumnRepository yaColumnRepository;
	
	@Override
	public void updatePost(long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(long id) {
		YAColumn yaColumn = yaColumnRepository.findOne(id);
		yaColumn.setDeleteYN('Y');
		save(yaColumn);
	}

	@Override
	public List<YAColumn> fetchPostsById() {
		return yaColumnRepository.findAllYAColumnsWhereDeleteYNIsN();
	}

	@Override
	public void save(YAColumn resource) {
		yaColumnRepository.save(resource);
	}

	@Override
	public YAColumn findOne(long id) {
		return yaColumnRepository.findOne(id);
	}

}
