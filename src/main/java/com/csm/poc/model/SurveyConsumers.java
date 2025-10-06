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
public class SurveyConsumers {
    @JsonProperty(Defines.SurveyId)
    private String surveyId;
    private long count;
    private List<String> users;
}
