package com.csm.poc.service;

import com.csm.poc.model.QueryFilters;
import com.csm.poc.model.ReportCount;
import com.csm.poc.model.Survey;

import java.util.List;

public interface ReportService {
    List<ReportCount> getReportCountByFilter(QueryFilters queryFilter);
}
