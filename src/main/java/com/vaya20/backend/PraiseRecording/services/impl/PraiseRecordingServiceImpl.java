package com.vaya20.backend.PraiseRecording.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya20.backend.PraiseRecording.domain.PraiseRecording;
import com.vaya20.backend.PraiseRecording.repositories.PraiseRecordingRepository;
import com.vaya20.backend.PraiseRecording.services.PraiseRecordingService;

@Service
public class PraiseRecordingServiceImpl implements PraiseRecordingService {

	@Autowired
	PraiseRecordingRepository praiseRecordingRepository;
	
	@Override
	public void updatePost(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		PraiseRecording praiseRecording = praiseRecordingRepository.findOne(id);
		praiseRecording.setDeleteYN('Y');
		save(praiseRecording);
	}

	@Override
	public List<PraiseRecording> fetchPostsById() {
		return praiseRecordingRepository.findAllPraiseRecordingsWhereDeleteYNIsN();
	}

	@Override
	public void save(PraiseRecording resource) {
		praiseRecordingRepository.save(resource);
		
	}

	@Override
	public PraiseRecording findOne(long id) {
		return praiseRecordingRepository.findOne(id);
	}


}
