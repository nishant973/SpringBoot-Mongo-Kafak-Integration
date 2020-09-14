package com.nishant.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nishant.model.BodyInfo;

@Repository
public interface BodyRepository extends MongoRepository<BodyInfo, String>{

	@Query("{type : ?0}")
	List<BodyInfo> findByType(String type); 
	
	
}
