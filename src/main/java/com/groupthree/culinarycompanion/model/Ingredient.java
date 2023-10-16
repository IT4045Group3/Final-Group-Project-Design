package com.groupthree.culinarycompanion.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.IDENTITY;

public class Ingredient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int ingredientId;
    private String name;
    // Getters and setters
}

