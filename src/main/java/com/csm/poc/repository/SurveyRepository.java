package com.csm.poc.repository;

import com.csm.poc.model.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends MongoRepository<Survey,Long> {

    @Query("{surveyId:?0}")
    Optional<Survey> findBySurveyId(String surveyId);

    }
