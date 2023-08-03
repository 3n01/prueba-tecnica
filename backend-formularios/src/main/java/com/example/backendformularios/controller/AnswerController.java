package com.example.backendformularios.controller;

import com.example.backendformularios.model.*;
import com.example.backendformularios.service.AnswersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/answers")
public class AnswerController {

    private final AnswersService answersService;

    public AnswerController(AnswersService AnswersService) {
        this.answersService = AnswersService;
    }

    @PostMapping("/save")
    public ResponseEntity<AnswersResponse> saveAnswers(@RequestBody AnswersRequest answersRequest){
        try{
            Answers saved = this.answersService.saveAnswers(answersRequest);
            return new ResponseEntity<>(new AnswersResponse(saved.getId(), saved.getUserid(), saved.getId(), "Answers saved successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new AnswersResponse(null, null, null,"Error saving answers from form id: " + answersRequest.getFormId()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Answers>> getAnswersByUserId(@PathVariable Long userId) {
        try {
            List<Answers> answers = this.answersService.getAnswersByUserId(userId);
            if (ObjectUtils.isEmpty(answers)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(answers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("dashboard/{userId}")
    public ResponseEntity<Dashboard> getDashboard(@PathVariable Long userId) {
        try {
            Dashboard dashboard = this.answersService.getDashboard(userId);
            if (dashboard == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(dashboard, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
