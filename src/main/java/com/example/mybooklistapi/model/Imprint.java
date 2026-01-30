package com.example.mybooklistapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "imprint")
public class Imprint {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    public Imprint() {
    }

    public Imprint(String name) {
        this.name = name;
    }

}