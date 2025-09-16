package com.csm.poc.controller;

import com.csm.poc.model.QueryFilters;
import com.csm.poc.model.ReportCount;
import com.csm.poc.model.Survey;
import com.csm.poc.service.QueryService;
import com.csm.poc.service.ReportService;
import com.csm.poc.service.SurveyService;
import com.csm.poc.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("report/surveys")
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
}
