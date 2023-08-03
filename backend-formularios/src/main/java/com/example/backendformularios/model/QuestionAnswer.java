package com.example.backendformularios.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionAnswer {
    private String question;
    private String answer;
    // Cada pregunta puede requerir una respueta tipo String, int, boolean, etc
    private String datatype;


}
