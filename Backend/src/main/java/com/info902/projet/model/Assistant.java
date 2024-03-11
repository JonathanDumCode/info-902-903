package com.info902.projet.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "assistant")
public class Assistant {

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "my_sequence"),
                    @Parameter(name = "initial_value", value = "100000"),
                    @Parameter(name = "increment_size", value = "1"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            }
    )
    private Integer code;

    private String language;

    private String voice;

    private String wifiSSID;

    private String wifiPassword;

    @OneToMany(mappedBy = "assistant")
    private List<History> history;

}
