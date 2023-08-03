package com.example.backendformularios.service;

import com.example.backendformularios.model.*;
import com.example.backendformularios.repository.AnswerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswersService {
    private final AnswerRepository answersRepository;
    private final UserService userService;

    public AnswersService(AnswerRepository formRepository, UserService userService) {
        this.answersRepository = formRepository;
        this.userService = userService;
    }


    public Answers saveAnswers(AnswersRequest answers) {
        User userById = userService.getUserById(answers.getUserId());
        if (userById == null){
            return null;
        }
        return answersRepository.save(mapToAnswers(answers));
    }

    public List<Answers> getAnswersByUserId(Long userId) {
        User userById = userService.getUserById(userId);
        if (userById != null){
            return answersRepository.findAllByUserid(userId);
        }
        return null;
    }


    private Answers mapToAnswers(AnswersRequest answersRequest){
        Answers answers = new Answers();
        answers.setAnswers(answersToString(answersRequest.getAnswers()));
        answers.setFormid(answersRequest.getFormId());
        answers.setUserid(answersRequest.getUserId());
        answers.setStatistics(answersRequest.isStatistics());
        return answers;
    }

    private String answersToString(List<QuestionAnswer> questionList){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(questionList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // se debería controlar esto
        return  null;
    }



    public Dashboard getDashboard(Long userId) {
        Dashboard result = new Dashboard();
        User userById = userService.getUserById(userId);
        if (userById != null){
            List<Answers> allByUserid = answersRepository.findAllByUserid(userId);
            allByUserid.forEach( a -> {
                if (a.isStatistics()){
                    // en función de si se van a mostrar estadísticas o no, el objeto dashboard será
                    // modicado y se traducirá en un json diferente que será utilizado por el front
                }
            });
            return result;
        }
        return null;
    }
}
