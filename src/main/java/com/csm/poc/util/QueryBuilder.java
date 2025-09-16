package com.csm.poc.util;

import com.csm.poc.model.QueryFilters;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class QueryBuilder {

    public Criteria buildFilterCriteria(QueryFilters queryFilter){
        if (queryFilter ==null)
            return new Criteria();

        ArrayList<Criteria> criteriaList =new ArrayList<>();
        addMatchingCriteriaFilter(Defines.Created_By,queryFilter.getCreatedBy(),criteriaList);
        addMatchingCriteriaFilter("category",queryFilter.getCategory(),criteriaList);
        addMatchingCriteriaFilter("state",queryFilter.getState(),criteriaList);
        addContainingCriteriaFilter("title"
                ,queryFilter.getTitle(),criteriaList);
        addDateCriteriaFilter(Defines.Created_At
                ,queryFilter.getStart(),queryFilter.getEnd(),criteriaList);

        if (criteriaList.isEmpty())
            return new Criteria();
        else
            return new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
    }

    private void addMatchingCriteriaFilter(String key, String value, ArrayList<Criteria> criteriaList){
        if(value !=null){
            criteriaList.add(Criteria.where(key).is(value));
        }
    }
    private void addContainingCriteriaFilter(String key, String value, ArrayList<Criteria> criteriaList){
        if(value !=null){
            criteriaList.add(Criteria.where(key).regex(value,"i"));
        }
    }

    private void addDateCriteriaFilter(String key, Date start, Date end, ArrayList<Criteria> criteriaList){
        if(start !=null && end !=null){
            criteriaList.add(Criteria.where(key).gte(start).lte(end));
        }
    }
}
