package com.example.backendformularios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // Esta es la url pública que el front le pasará al cliente para que acceda a una pantalla
    // donde el cliente verá todos los formularios a los que tiene acceso
    private String url;

}
