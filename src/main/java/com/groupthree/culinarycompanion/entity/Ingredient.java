package com.groupthree.culinarycompanion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int ingredientId;
    private String name;
    @ManyToOne
    private Recipe recipe;
    // Getters and setters
}

