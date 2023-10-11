package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.RecipeCollectionDTO;

import java.util.List;

public interface IRecipeCollectionService {
    void createCollection(RecipeCollectionDTO collectionDTO);
    void addRecipeToCollection(int collectionId, int recipeId);
    void removeRecipeFromCollection(int collectionId, int recipeId);
    RecipeCollectionDTO findCollectionById(int collectionId);
    List<RecipeCollectionDTO> findCollectionsByUserId(int userId);
}

