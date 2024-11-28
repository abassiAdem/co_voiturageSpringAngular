package com.example.co_voiturage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String depart;
    private String destination;
    private String dateDepart;
    private int nbrPlaces;
    private double price;
    private String restrictions;
    private Long userId;
    // Getters and Setters
}
