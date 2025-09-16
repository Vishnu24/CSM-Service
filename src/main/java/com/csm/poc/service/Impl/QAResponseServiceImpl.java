package com.csm.poc.service.Impl;

import com.csm.poc.model.QAResponse;
import com.csm.poc.model.Survey;
import com.csm.poc.repository.QAResponseRepository;
import com.csm.poc.service.QAService;
import com.csm.poc.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QAResponseServiceImpl implements QAService {

    @Autowired
    private QAResponseRepository qaRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public QAResponse saveAnswer(QAResponse answer) {
        answer.setRespondedAt(new Date());
        answer.setId(sequenceGeneratorService.generateSequence(Survey.SEQUENCE_NAME));
        return qaRepository.save(answer);
    }
}