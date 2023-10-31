package com.groupthree.culinarycompanion.dto;

import com.groupthree.culinarycompanion.entity.Recipe;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class RecipeCollectionDTO {
    private int collectionId;
    private int userId;
    private List<Recipe> recipes = new ArrayList<>();
}
