package com.example.backendformularios.repository;

import com.example.backendformularios.model.Form;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends CrudRepository<Form, Long> {
    List<Form> findAllByUserid(long userId);
    boolean existsByUserid(long userId);
    Form findByQuestions(String questions);
}
