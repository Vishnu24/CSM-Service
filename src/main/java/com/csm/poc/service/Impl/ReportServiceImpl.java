package com.csm.poc.service.Impl;

import com.csm.poc.model.*;
import com.csm.poc.service.ReportService;
import com.csm.poc.util.Defines;
import com.csm.poc.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {


    @Autowired
    private QueryBuilder queryBuilder;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ReportCount> getReportCountByFilter(QueryFilters queryFilter) {
        List<ReportCount> mappingResult = getReportResult(queryFilter);
        long sum = mappingResult.stream().mapToLong(ReportCount::getCount).sum();

        return mappingResult.stream().map(
                        report -> new ReportCount(report.getField(),
                                report.getCount(), report.getTotal(), (report.getCount() * 100) / sum))
                .sorted((a, b) -> Long.compare(a.getCount(), b.getCount()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyConsumer> getPartcipationList(QueryFilters queryFilter) {

        Criteria criteria = queryBuilder.buildFilterCriteria(queryFilter);
        List<String> surveyIds = mongoTemplate.findDistinct(new Query(criteria), Defines.SurveyId,
                Survey.class, String.class);
        ArrayList<SurveyConsumer> consumers = new ArrayList<>();
        for (String surveyId : surveyIds) {
            Criteria searchCtr = Criteria.where(Defines.SurveyId).is(surveyId);
            List<String> users = mongoTemplate.findDistinct(new Query(searchCtr), Defines.Responded_By,
                    QAResponse.class, String.class);
            consumers.add(new SurveyConsumer(surveyId, users.size(), users));
        }

        return consumers;
    }

    @Override
    public List<ReportCount> getDistributionReport(String questionId, String surveyID) {
        List<ReportCount> mappingResult = getDistributionReportCriteria(questionId, surveyID);
        long sum = mappingResult.stream().mapToLong(ReportCount::getCount).sum();

        return mappingResult.stream().map(
                        report -> new ReportCount(report.getField(),
                                report.getCount(), report.getTotal(), (report.getCount() * 100) / sum))
                .sorted((a, b) -> Long.compare(a.getCount(), b.getCount()))
                .collect(Collectors.toList());
    }

    private List<ReportCount> getReportResult(QueryFilters queryFilter) {

        Criteria criteria = queryBuilder.buildFilterCriteria(queryFilter);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group(queryFilter.getGroupBy()).count().as("count"),
                Aggregation.project("count").and("_id").as("field"),
                Aggregation.sort(Sort.Direction.DESC, "count")
        );
        System.out.println(aggregation.toString());
        AggregationResults<ReportCount> reportResult = mongoTemplate.aggregate(aggregation, "surveys", ReportCount.class);

        return reportResult.getMappedResults();
    }

    private List<ReportCount> getDistributionReportCriteria(String questionId, String surveyId) {

        Criteria criteria = queryBuilder.buildDistributionCriteria(questionId, surveyId);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("answerText").count().as("count"),
                Aggregation.project("count").and("_id").as("field"),
                Aggregation.sort(Sort.Direction.DESC, "count")
        );
        System.out.println(aggregation.toString());
        AggregationResults<ReportCount> reportResult = mongoTemplate.aggregate(aggregation, "qa_responses", ReportCount.class);

        return reportResult.getMappedResults();
    }
}
