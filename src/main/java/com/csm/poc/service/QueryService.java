package com.csm.poc.service;

import com.csm.poc.model.QAFilter;
import com.csm.poc.model.QueryFilters;
import com.csm.poc.model.Survey;

import java.util.List;

public interface QueryService {
    Survey getSurveyBySurveyId(String surveyId);
    List<Survey> getAllSurveyByFilter(QueryFilters queryFilter);
    List<Survey> getAllSurveyByQAFilter(QAFilter queryFilter);
}
