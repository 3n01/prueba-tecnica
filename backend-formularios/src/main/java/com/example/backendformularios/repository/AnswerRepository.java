package com.example.backendformularios.repository;

import com.example.backendformularios.model.Answers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answers, Long> {
    List<Answers> findAllByUserid(long userId);
    boolean existsByUserid(long userId);
}
