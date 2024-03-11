package com.info902.projet.service;

import com.info902.projet.model.Assistant;
import com.info902.projet.repository.AssistantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AssistantService {

    @Autowired
    private AssistantRepository assistantRepository;

    public Integer createAssistant(){
        Assistant newAssistant = Assistant.builder().language("fr").voice("basic").build();
        assistantRepository.save(newAssistant);
        return newAssistant.getCode();
    }

}
