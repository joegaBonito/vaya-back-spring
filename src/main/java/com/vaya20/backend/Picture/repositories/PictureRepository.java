package com.vaya20.backend.Picture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vaya20.backend.Picture.domain.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
	@Query("SELECT p FROM Picture p WHERE p.deleteYN = 'N' AND p.pictureList.id = :categoryId ORDER BY p.id ASC")
	public List<Picture> findAllPicturesWhereDeleteYNIsNAndCategoryIdMatches(@Param("categoryId") long categoryId);
}
