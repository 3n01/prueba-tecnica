package com.example.backendformularios.repository;

import com.example.backendformularios.model.Form;
import com.example.backendformularios.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String username);
}
