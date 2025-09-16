package com.csm.poc.controller;

import com.csm.poc.model.QAResponse;
import com.csm.poc.service.QAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qa")
public class QAController {

    @Autowired
    private QAService qaService;

    @PostMapping
    public ResponseEntity<QAResponse> saveAnswer(@RequestBody QAResponse answer){
        return new ResponseEntity<QAResponse>(qaService.saveAnswer(answer), HttpStatus.CREATED);
    }
}
