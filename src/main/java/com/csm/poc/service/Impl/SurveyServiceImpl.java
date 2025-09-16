package com.csm.poc.service.Impl;

import com.csm.poc.model.Survey;
import com.csm.poc.repository.SurveyRepository;
import com.csm.poc.service.SequenceGeneratorService;
import com.csm.poc.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {


    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    //save employee in database
    @Override
    public Survey saveSurvey(Survey survey){
        survey.setCreatedAt(new Date());
        survey.setId(sequenceGeneratorService.generateSequence(Survey.SEQUENCE_NAME));
        return surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurvey() {
        return  surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(long id) {
        Optional<Survey> employee =  surveyRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public Survey updateSurvey(Survey Survey, long id) {
        Survey existingSurvey = surveyRepository.findById(id).orElseThrow(
                RuntimeException::new
        );
        // save
        existingSurvey.setModifiedAt(new Date());
        surveyRepository.save(existingSurvey);
        return existingSurvey;
    }

    @Override
    public void deleteESurvey(long id) {
        surveyRepository.findById(id).orElseThrow(RuntimeException::new);
        //delete
        surveyRepository.deleteById(id);
    }


}
