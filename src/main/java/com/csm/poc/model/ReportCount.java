package com.csm.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCount
{
    @Field("_id")
    private  String field;
    private int count;
    private int total;
    private double percentage;
}
