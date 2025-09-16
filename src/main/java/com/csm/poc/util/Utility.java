package com.csm.poc.util;

import com.csm.poc.model.QueryFilters;
import com.csm.poc.model.State;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    public static QueryFilters buildQueryFilter(String createdBy, String title, String category, String state, String start, String end, String groupBy) {
        QueryFilters filter= new QueryFilters();
        filter.setTitle(title);
        filter.setCategory(category);
        filter.setCreatedBy(createdBy);
        filter.setEnd(parseDate(end));
        filter.setStart(parseDate(start));
        filter.setState(state);
        if(groupBy !=null)
            filter.setGroupBy(groupBy);
        return filter;
    }

    private static Date parseDate(String dateString){
        if(dateString == null)
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(Defines.DATE_FORMAT);
        try {
            Date date = formatter.parse(dateString);
            System.out.println("Parsed Date: " + date);
            return date;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return  null;
    }

}
