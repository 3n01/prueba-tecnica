package com.example.backendformularios.service;

import com.example.backendformularios.model.Form;
import com.example.backendformularios.model.FormRequest;
import com.example.backendformularios.model.QuestionAnswer;
import com.example.backendformularios.model.User;
import com.example.backendformularios.repository.FormRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {
    private final FormRepository formRepository;
    private final UserService userService;

    public FormService(FormRepository formRepository, UserService userService) {
        this.formRepository = formRepository;
        this.userService = userService;
    }


    public Form saveForm(FormRequest form) {
        User userById = userService.getUserById(form.getUserId());
        if (userById == null){
            return null;
        }
        if (formRepository.existsByUserid(form.getUserId())){
            // Esta findByQuestions tal y como está es problemática, debería usar JSON_CONTAINS en una native query o algo así
            Form byQuestions = formRepository.findByQuestions(questionsToString(form.getQuestions()));
            if (byQuestions != null){
                // Ya existe el formulario | usuario
                return mapToForm(form);
            }else{
                // Insertar nuevo formulario | usuario
                return formRepository.save(mapToForm(form));
            }

        }else{
            // insertar nuevo formulario | usuario
            return formRepository.save(mapToForm(form));
        }
    }

    public Form findFormById(Long id) {
        return formRepository.findById(id).orElse(null);
    }

    private Form mapToForm(FormRequest formRequest){
        Form form = new Form();
        form.setTitle(formRequest.getTitle());
        form.setQuestions(questionsToString(formRequest.getQuestions()));
        form.setUserId(formRequest.getUserId());
        return form;
    }

    private String questionsToString(List<QuestionAnswer> questionList){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(questionList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // se debería controlar esto
        return  null;
    }

    public List<Form> getFormsByUserId(Long userId) {
        User userById = userService.getUserById(userId);
        if (userById != null){
            return formRepository.findAllByUserid(userId);
        }
        return null;
    }
}
