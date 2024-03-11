package com.info902.projet.service;

import com.info902.projet.controller.response.ConfigResponse;
import com.info902.projet.model.Assistant;
import com.info902.projet.model.User;
import com.info902.projet.repository.AssistantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ConfigResponse getConfig(Integer code){
        var assistant = assistantRepository.findByCode(code).orElseThrow();
        ConfigResponse configResponse = new ConfigResponse(assistant.getLanguage(), assistant.getVoice(), assistant.getWifiSSID(), assistant.getWifiPassword());
        return configResponse;
    }

}
