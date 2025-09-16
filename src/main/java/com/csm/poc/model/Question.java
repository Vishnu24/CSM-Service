package com.csm.poc.model;

import com.csm.poc.util.Defines;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @JsonProperty(Defines.QuestionId)
    private String questionId;

    private String type;
    private String dimension;
    private boolean required;

    private HashMap<String, Object> distribution;

}
