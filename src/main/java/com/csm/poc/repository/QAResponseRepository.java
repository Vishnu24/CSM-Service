package com.csm.poc.repository;

import com.csm.poc.model.QAResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QAResponseRepository extends MongoRepository<QAResponse,Long> {

}
