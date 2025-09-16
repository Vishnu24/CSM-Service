package com.csm.poc.service.Impl;

import com.csm.poc.model.QAFilter;
import com.csm.poc.model.QAResponse;
import com.csm.poc.model.QueryFilters;
import com.csm.poc.model.Survey;
import com.csm.poc.repository.SurveyRepository;
import com.csm.poc.service.QueryService;
import com.csm.poc.util.Defines;
import com.csm.poc.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QueryBuilder queryBuilder;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Survey getSurveyBySurveyId(String surveyId) {
        Optional<Survey> employee = surveyRepository.findBySurveyId(surveyId);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Survey> getAllSurveyByFilter(QueryFilters queryFilter) {
        return mongoTemplate.find(new Query(queryBuilder.buildFilterCriteria(queryFilter)), Survey.class);
    }

    @Override
    public List<Survey> getAllSurveyByQAFilter(QAFilter qaFilter) {
        Query query =new Query(queryBuilder.buildFilterCriteria(qaFilter));
        List<String> surveyIds = mongoTemplate.findDistinct(query, Defines.SurveyId,
                QAResponse.class, String.class);
        if(surveyIds.isEmpty()) {
            List<Survey> surveys = List.of();
            return surveys;
        }
        Criteria searchCtr=Criteria.where(Defines.SurveyId).in(surveyIds);
        return mongoTemplate.find(new Query(searchCtr), Survey.class);
    }


}
