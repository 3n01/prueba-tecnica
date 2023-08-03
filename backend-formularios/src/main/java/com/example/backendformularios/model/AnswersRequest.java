package com.example.backendformularios.model;

import lombok.Data;

import java.util.List;
@Data
public class AnswersRequest {
    private Long formId;
    private Long userId;
    private List<QuestionAnswer> answers;
    private boolean statistics;


}