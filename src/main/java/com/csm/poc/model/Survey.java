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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "surveys")
public class Survey {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    @JsonProperty(Defines.SurveyId)
    private String surveyId;

    private String title;
    private String category;
    private State state;

    @JsonProperty(Defines.Created_By)
    private String createdBy;

    @JsonProperty(Defines.Created_At)
    @Field(name=Defines.Created_At, targetType= FieldType.TIMESTAMP)
    private Date createdAt;

    @JsonProperty(Defines.Modified_By)
    private String modifiedBy;

    @JsonProperty(Defines.Modified_At)
    @Field(name=Defines.Modified_At, targetType= FieldType.TIMESTAMP)
    private Date modifiedAt;

    @JsonProperty(Defines.Questions)
     private  List<Question> questions;
}
