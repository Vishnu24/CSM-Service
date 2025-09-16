package com.csm.poc.service.Impl;

import com.csm.poc.model.QueryFilters;
import com.csm.poc.model.ReportCount;
import com.csm.poc.service.ReportService;
import com.csm.poc.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class ReportServiceImpl implements ReportService {


    @Autowired
    private QueryBuilder queryBuilder;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ReportCount> getReportCountByFilter(QueryFilters queryFilter) {
        List<ReportCount> mappingResult = getReportResult(queryFilter);
         long sum= mappingResult.stream().mapToLong(ReportCount ::getCount).sum();

        return  mappingResult.stream().map(
                report -> new ReportCount(report.getField(),
                        report.getCount(), report.getTotal(),(report.getCount()*100)/sum ))
                .sorted((a,b)-> Long.compare(a.getCount(),b.getCount()))
                .collect(Collectors.toList());
    }

    private List<ReportCount> getReportResult(QueryFilters queryFilter){

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
}
