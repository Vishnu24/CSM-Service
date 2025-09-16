package com.csm.poc.model;

import com.csm.poc.util.Defines;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "qa_responses")
public class QAResponse {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;
    @JsonProperty(Defines.SurveyId)
    private String surveyId;

    @JsonProperty(Defines.QuestionId)
    private String questionId;

    @JsonProperty(Defines.Responded_By)
    private String respondedBy;

    @JsonProperty(Defines.Responded_At)
    @Field(name=Defines.Responded_At, targetType= FieldType.TIMESTAMP)
    private Date respondedAt;

    private String role;
    private String type;
    private String dimension;

    private long answer;
    private String answerText;


}
