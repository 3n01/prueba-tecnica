package com.example.backendformularios.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnswersResponse {
    private Long id;
    private Long userId;
    private Long formId;
    private String message;



}
