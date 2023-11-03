package com.groupthree.culinarycompanion.entity;

import java.util.List;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int recipeId;
    private String name;
    @ManyToOne
    private Cuisine cuisine; //ex: Mexican cuisine, Italian Cuisine, Chinese cuisine, etc
    @Enumerated(EnumType.STRING)
    private RecipeType type;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @ManyToMany
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipeId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private List<Ingredient> ingredients;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Instruction> instructions;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Photo> photos;
    @ManyToOne
    private User user;
    @ManyToMany
    @JoinTable(
            name = "recipe_collection",
            joinColumns = @JoinColumn(name = "recipeId"),
            inverseJoinColumns = @JoinColumn(name = "collectionId")
    )
    private List<Collection> collections;

    public enum Difficulty {
        EASY,
        MODERATE,
        HARD
    }
    public enum RecipeType {
        APPETIZER,
        MAIN_COURSE,
        DESSERT,
        SIDE_DISH,
        DRINK,
        OTHER
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }
}
