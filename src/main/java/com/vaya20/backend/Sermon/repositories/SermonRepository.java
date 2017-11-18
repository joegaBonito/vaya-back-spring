package com.vaya20.backend.Sermon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya20.backend.Sermon.domain.SermonPost;

@Repository
public interface SermonRepository extends CrudRepository<SermonPost,Long> {

	@Query("SELECT sp FROM SermonPost sp WHERE sp.deleteYN = 'N' ORDER BY sp.id ASC")
	public List<SermonPost> findAllPostsWhereDeleteYNIsN();
}
