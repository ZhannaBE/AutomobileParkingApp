
package com.example.parking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ModelEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double cost;

    public ModelEntity(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }

    public void addAttribute(String username, String name) {

    }

    public String getName() {
        return name;
    }
}
