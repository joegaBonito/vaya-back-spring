package com.vaya20.backend.PraiseRecording.services;

import java.util.List;

import com.vaya20.backend.PraiseRecording.domain.PraiseRecording;

public interface PraiseRecordingService {

	public void updatePost(long id);
	public void delete(long id);
	public List<PraiseRecording> fetchPostsById();
	public void save(PraiseRecording resource);
	public PraiseRecording findOne(long id);
}
