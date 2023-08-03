package com.example.backendformularios.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormResponse{
    private Long userId;
    private Long formId;
    private String mensaje;


}
