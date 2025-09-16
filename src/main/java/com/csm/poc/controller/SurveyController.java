package com.csm.poc.controller;

import com.csm.poc.model.Survey;
import com.csm.poc.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @PostMapping
    public ResponseEntity<Survey> saveSurvey(@RequestBody Survey survey){
        return new ResponseEntity<Survey>(surveyService.saveSurvey(survey), HttpStatus.CREATED);
    }
    //GetAll Rest Api
    @GetMapping
    public ResponseEntity<List<Survey>> getAllSurveys(){
        return new ResponseEntity<List<Survey>>(surveyService.getAllSurvey(),HttpStatus.OK);

    }

    //Get by Id Rest Api
    @GetMapping("{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable("id") long surevyDocId){
        return new ResponseEntity<Survey>(surveyService.getSurveyById(surevyDocId),HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable("id") long id,
                                                   @RequestBody Survey surevy){
        return new ResponseEntity<Survey>(surveyService.updateSurvey(surevy,id),HttpStatus.OK);
    }

    //Delete Rest Api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable("id") long id){
        //delete Survey from db
        surveyService.deleteESurvey(id);
        return new ResponseEntity<String>("Survey deleted Successfully.",HttpStatus.OK);
    }
}
