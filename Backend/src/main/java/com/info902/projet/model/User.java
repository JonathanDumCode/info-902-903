package com.info902.projet.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pseudo;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Assistant> assistants;

}
