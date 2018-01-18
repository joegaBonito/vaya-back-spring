package com.vaya20.backend.Picture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya20.backend.Picture.domain.PictureList;

@Repository
public interface PictureListRepository extends CrudRepository<PictureList, Long> {
	@Query("SELECT p FROM PictureList p WHERE p.deleteYN = 'N' ORDER BY p.id ASC")
	public List<PictureList> findAllPictureListsWhereDeleteYNIsN();
}
