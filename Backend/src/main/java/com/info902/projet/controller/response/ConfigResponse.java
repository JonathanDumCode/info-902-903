package com.info902.projet.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfigResponse {

    private String language;

    private String voice;

    private String wifiSSID;

    private String wifiPassword;

}
