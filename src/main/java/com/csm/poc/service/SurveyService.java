package com.csm.poc.service;

import com.csm.poc.model.Survey;

import java.util.List;

public interface SurveyService {
    Survey saveSurvey(Survey Survey);
    List<Survey> getAllSurvey();
    Survey getSurveyById(long id);
    Survey updateSurvey(Survey Survey,long id);
    void deleteESurvey(long id);
}
