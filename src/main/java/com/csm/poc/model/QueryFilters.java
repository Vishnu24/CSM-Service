package com.csm.poc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryFilters {
    private String createdBy;
    private String title;
    private String category;
    private String state;
    private Date start;
    private Date end;



    private String groupBy;
}
