package com.info902.projet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "history")
public class History {

    @ManyToOne
    @JoinColumn(name = "code", nullable = false)
    private Assistant assistant;
    @Id
    private Integer id;
    private String request;

    private String response;

    private Date date;



}
