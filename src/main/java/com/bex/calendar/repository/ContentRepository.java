package com.bex.calendar.repository;

import com.bex.calendar.model.Content;
import com.bex.calendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ContentRepository extends CrudRepository<Content, Integer> {
	List<Content> findAllByTitleContains(String keyword);

	@Query("""
			SELECT *
			FROM Content c
			WHERE c.status = :status
			""")
	List<Content> listByStatus(@Param("status") Status status);
}
