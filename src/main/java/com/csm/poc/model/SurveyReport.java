package com.csm.poc.model;

import com.csm.poc.util.Defines;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyReport {
    @JsonProperty(Defines.SurveyId)
    private String surveyId;
    private String title;
    private String producer;
    private long responseCount;
    private String state;
    private String createdOn;
}
