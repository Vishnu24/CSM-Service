package com.csm.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QAFilter {
    private String role;
    private String respondedBy;
    private String type;
    private String dimension;
    private Date start;
    private Date end;
    private String groupBy;
}
