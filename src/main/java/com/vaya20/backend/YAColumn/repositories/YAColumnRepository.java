package com.vaya20.backend.YAColumn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya20.backend.YAColumn.domain.YAColumn;

@Repository
public interface YAColumnRepository extends CrudRepository<YAColumn, Long> {
	@Query("SELECT yac FROM YAColumn yac WHERE yac.deleteYN = 'N' ORDER BY yac.id ASC")
	public List<YAColumn> findAllYAColumnsWhereDeleteYNIsN();
}
