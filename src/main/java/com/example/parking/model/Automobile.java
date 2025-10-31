
package com.example.parking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Automobile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ModelEntity model;
    @ManyToOne
    private User owner;

    public Automobile(ModelEntity model, User owner) {
        this.model = model;
        this.owner = owner;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
