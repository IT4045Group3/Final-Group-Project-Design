package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.repository.RecipeCollectionRepository;
import com.groupthree.culinarycompanion.dto.RecipeCollectionDTO;
import com.groupthree.culinarycompanion.entity.RecipeCollection;

import java.util.List;

public class RecipeCollectionService implements IRecipeCollectionService {
    private RecipeCollectionRepository collectionDAO;

    public RecipeCollectionService(RecipeCollectionRepository collectionDAO) {
        this.collectionDAO = collectionDAO;
    }

    @Override
    public void createCollection(RecipeCollectionDTO collectionDTO) {
        RecipeCollection collection = mapDTOToModel(collectionDTO);
        collectionDAO.createCollection(collection);
    }

    @Override
    public void addRecipeToCollection(int collectionId, int recipeId) {
        collectionDAO.addRecipeToCollection(collectionId, recipeId);
    }

    @Override
    public void removeRecipeFromCollection(int collectionId, int recipeId) {
        collectionDAO.removeRecipeFromCollection(collectionId, recipeId);
    }

    @Override
    public RecipeCollectionDTO findCollectionById(int collectionId) {
        RecipeCollection collection = collectionDAO.findCollectionById(collectionId);
        return mapModelToDTO(collection);
    }

    @Override
    public List<RecipeCollectionDTO> findCollectionsByUserId(int userId) {
        List<RecipeCollection> collections = collectionDAO.findCollectionsByUserId(userId);
        return collections.stream()
                .map(this::mapModelToDTO)
                .toList();
    }

    private RecipeCollectionDTO mapModelToDTO(RecipeCollection collection) {
        RecipeCollectionDTO dto = new RecipeCollectionDTO();
        dto.setCollectionId(collection.getCollectionId());
        dto.setUserId(collection.getUserId());
        dto.setRecipeIds(collection.getRecipeIds());
        return dto;
    }

    private RecipeCollection mapDTOToModel(RecipeCollectionDTO dto) {
        RecipeCollection collection = new RecipeCollection();
        collection.setCollectionId(dto.getCollectionId());
        collection.setUserId(dto.getUserId());
        collection.setRecipeIds(dto.getRecipeIds());
        return collection;
    }
}
