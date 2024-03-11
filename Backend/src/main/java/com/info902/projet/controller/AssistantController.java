package com.info902.projet.controller;

import com.info902.projet.controller.response.ConfigResponse;
import com.info902.projet.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("assistant")
public class AssistantController {

    @Autowired
    private AssistantService assistantService;
    @GetMapping("/login")
    public Integer AssistantLogin(){
        return assistantService.createAssistant();
    }

    @GetMapping("/config/{code}")
    public ConfigResponse AssistantConfig(@PathVariable Integer code){

        return assistantService.getConfig(code);
    }
}
