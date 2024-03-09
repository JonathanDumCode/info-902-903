package com.info902.projet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "assistant")
public class Assistant {

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;
    @Id
    @GeneratedValue
    private Integer code;

    private String language;

    private String voice;

    private String wifiSSID;

    private String wifiPassword;

    @OneToMany(mappedBy = "assistant")
    private List<History> history;

}
