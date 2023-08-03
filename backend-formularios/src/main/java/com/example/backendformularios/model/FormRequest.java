package com.example.backendformularios.model;

import lombok.Data;

import java.util.List;

@Data
public class FormRequest {
    private Long userId;
    private String title;
    private List<QuestionAnswer> questions;

}
