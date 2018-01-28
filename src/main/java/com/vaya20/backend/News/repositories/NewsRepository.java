package com.vaya20.backend.News.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya20.backend.News.domain.News;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
	@Query("SELECT n FROM News n WHERE n.deleteYN = 'N' ORDER BY n.id ASC")
	public List<News> findNewsById();
}
