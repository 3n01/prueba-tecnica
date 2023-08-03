package com.example.backendformularios.controller;

import com.example.backendformularios.model.Form;
import com.example.backendformularios.model.FormRequest;
import com.example.backendformularios.model.FormResponse;
import com.example.backendformularios.service.FormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/form")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/save")
    public ResponseEntity<FormResponse> saveForm(@RequestBody FormRequest formRequest){
        try{
            Form saved = this.formService.saveForm(formRequest);
            return new ResponseEntity<>(new FormResponse(saved.getUserId(), saved.getId(),"Form saved successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new FormResponse(null, null, "Error saving form " + formRequest.getTitle()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Form>> getFormsByUserId(@PathVariable Long userId) {
        try {
            List<Form> forms = this.formService.getFormsByUserId(userId);
            if (ObjectUtils.isEmpty(forms)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(forms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
