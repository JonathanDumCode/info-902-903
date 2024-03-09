package com.info902.projet.controller;

import com.info902.projet.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assistant")
public class AssistantController {

    @Autowired
    private AssistantService assistantService;
    @GetMapping("/login")
    public Integer AssistantLogin(){
        return assistantService.createAssistant();
    }
}
