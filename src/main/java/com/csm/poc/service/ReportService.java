package com.csm.poc.service;

import com.csm.poc.model.QueryFilters;
import com.csm.poc.model.ReportCount;
import com.csm.poc.model.SurveyConsumers;
import com.csm.poc.model.SurveyReport;

import java.util.List;

public interface ReportService {
    List<ReportCount> getReportCountByFilter(QueryFilters queryFilter);
    List<SurveyConsumers> getPartcipationList(QueryFilters queryFilter);
    List<ReportCount> getDistributionReport(String questionId, String surveyID);
    List<SurveyReport> getSurveyReport(QueryFilters queryFilter);

}
