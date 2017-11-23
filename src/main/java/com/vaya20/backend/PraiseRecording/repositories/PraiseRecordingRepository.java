package com.vaya20.backend.PraiseRecording.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya20.backend.PraiseRecording.domain.PraiseRecording;

@Repository
public interface PraiseRecordingRepository extends CrudRepository<PraiseRecording, Long> {
	@Query("SELECT pr FROM PraiseRecording pr WHERE pr.deleteYN = 'N' ORDER BY pr.id ASC")
	public List<PraiseRecording> findAllPraiseRecordingsWhereDeleteYNIsN();
}
