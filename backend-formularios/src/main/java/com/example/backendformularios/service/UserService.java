package com.example.backendformularios.service;

import com.example.backendformularios.model.User;
import com.example.backendformularios.model.UserRequest;
import com.example.backendformularios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Value("${urlpublica}")
    private String urlpublica;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setUrl(urlpublica + UUID.randomUUID() + "-" + userRequest.getName());
        return userRepository.save(user);
    }

    public User getUserById(long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }


    public User findByName(String username) {
        return userRepository.findByName(username);
    }
}
