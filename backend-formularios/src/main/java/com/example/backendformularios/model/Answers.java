package com.example.backendformularios.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long formid;
    private Long userid;
    private boolean statistics;

    @Column(columnDefinition = "JSON")
    private String answers;




}