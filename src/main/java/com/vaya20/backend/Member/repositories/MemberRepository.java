package com.vaya20.backend.Member.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vaya20.backend.Member.domain.Member;
import com.vaya20.backend.News.domain.News;


@Repository
public interface MemberRepository extends CrudRepository<Member,Long> {

	List<Member> findAllByOrderByEmail();

	Member findByEmail(String username);
	
	@Query("SELECT n FROM Member n WHERE n.deleteYN = 'N' ORDER BY n.id ASC")
	public List<Member> findEmailByIdAndDeleteYN();

	@Query("SELECT n.id FROM Member n WHERE n.email = :username AND n.deleteYN = 'N' ORDER BY n.id ASC")
	public Integer findIdByEmail(@Param("username") String username);
}
