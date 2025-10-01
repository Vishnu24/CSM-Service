package com.csm.poc.controller;

import com.csm.poc.model.*;
import com.csm.poc.service.QueryService;
import com.csm.poc.service.ReportService;
import com.csm.poc.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class QueryController {
    @Autowired
    private QueryService queryService;

    @Autowired
    private ReportService reportService;


    @GetMapping("query/surveys")
    public ResponseEntity<List<Survey>> getAllSurveyByFilter(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ){
        QueryFilters filter = Utility.buildQueryFilter(createdBy,title,category,state,start,end,null);
        return new ResponseEntity<List<Survey>>(queryService.getAllSurveyByFilter(filter), HttpStatus.OK);

    }


    @GetMapping("report/survey-filter")
    public ResponseEntity<List<ReportCount>> getReportByFilterAndGroup(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = true) String groupBy
    ){
        QueryFilters filter = Utility.buildQueryFilter(createdBy,title,category,state,start,end,groupBy);
        return new ResponseEntity<List<ReportCount>>(reportService.getReportCountByFilter(filter), HttpStatus.OK);
    }


    @GetMapping("query/consumer")
    public ResponseEntity<List<Survey>> getAllSurveyByResponder(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String dimension,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = true) String respondedBy
    ){
        QAFilter filter = Utility.buildQAFilter(role,dimension,type,respondedBy,start,end,null);
        return new ResponseEntity<List<Survey>>(queryService.getAllSurveyByQAFilter(filter), HttpStatus.OK);
    }

    @GetMapping("report/distribution/{surveyId}")
    public ResponseEntity<List<ReportCount>> getDistributionReport(
            @PathVariable("surveyId") String surveyID,
            @RequestParam(required = true) String questionId
    ){

        return new ResponseEntity<List<ReportCount>>(reportService.getDistributionReport(questionId,surveyID), HttpStatus.OK);
    }

    @GetMapping("report/users")
    public ResponseEntity<List<SurveyConsumers>> getParticipiationListBySurvey(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ){
        QueryFilters filter = Utility.buildQueryFilter(createdBy,title,category,state,start,end,null);
        return new ResponseEntity<List<SurveyConsumers>>(reportService.getPartcipationList(filter), HttpStatus.OK);
    }

    @GetMapping("report/surveys")
    public ResponseEntity<List<SurveyReport>> getSurveyDashboard(@RequestParam(required = false) String title,
                                                                    @RequestParam(required = false) String category,
                                                                    @RequestParam(required = false) String createdBy,
                                                                    @RequestParam(required = false) String state,
                                                                    @RequestParam(required = false) String start,
                                                                    @RequestParam(required = false) String end

    ){
        QueryFilters filter = Utility.buildQueryFilter(createdBy,title,category,state,start,end,null);
        return new ResponseEntity<List<SurveyReport>>(reportService.getSurveyReport(filter), HttpStatus.OK);
    }

}
