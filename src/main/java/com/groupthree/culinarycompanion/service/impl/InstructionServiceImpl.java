package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.repository.InstructionRepository;
import com.groupthree.culinarycompanion.repository.PhotoRepository;
import com.groupthree.culinarycompanion.repository.RecipeRepository;
import com.groupthree.culinarycompanion.dto.InstructionDTO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.entity.Instruction;
import com.groupthree.culinarycompanion.entity.Photo;
import com.groupthree.culinarycompanion.service.IInstructionService;
import com.groupthree.culinarycompanion.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructionServiceImpl implements IInstructionService {

    @Autowired
    private InstructionRepository instructionRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Instruction addInstructionToRecipe(int recipeId, Instruction newInstruction) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            newInstruction.setRecipe(recipe);
            return instructionRepository.save(newInstruction);
        }
        return null;
    }

    @Override
    public void updateInstructionInRecipe(int recipeId, int instructionId, Instruction updatedInstruction) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        Instruction existingInstruction = instructionRepository.findById(instructionId).orElse(null);

        if (recipe != null && existingInstruction != null) {

            if (updatedInstruction.getStepNumber() != existingInstruction.getStepNumber()) {
                existingInstruction.setStepNumber(updatedInstruction.getStepNumber());
            }
            if (updatedInstruction.getDescription() != null && !updatedInstruction.getDescription().equals(existingInstruction.getDescription())) {
                existingInstruction.setDescription(updatedInstruction.getDescription());
            }
            if (updatedInstruction.getVideoURL() != null && !updatedInstruction.getVideoURL().equals(existingInstruction.getVideoURL())) {
                existingInstruction.setVideoURL(updatedInstruction.getVideoURL());
            }
            if(updatedInstruction.getRecipe() != null && !updatedInstruction.getRecipe().equals(existingInstruction.getRecipe())) {
                existingInstruction.setRecipe((updatedInstruction.getRecipe()));
            }
            if(updatedInstruction.getPhotos() != null && !updatedInstruction.getPhotos().equals(existingInstruction.getPhotos())) {
                existingInstruction.setPhotos((updatedInstruction.getPhotos()));
            }

            instructionRepository.save(existingInstruction);
        }
    }



    @Override
    public void removeInstructionFromRecipe(int recipeId, int instructionId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            Instruction instruction = instructionRepository.findById(instructionId).orElse(null);
            if (instruction != null && instruction.getRecipe().getRecipeId() == recipeId) {
                instructionRepository.delete(instruction);
            }
        }
    }

    @Override
    public Instruction getInstructionById(int recipeId, int instructionId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            return instructionRepository.findByInstructionIdAndRecipe(instructionId, recipe);
        }
        return null;
    }

    @Override
    public void addPhotoInInstruction(int recipeId, int instructionId, String imagePath, String imageName) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            Instruction instruction = instructionRepository.findByInstructionIdAndRecipe(instructionId, recipe);
            if (instruction != null) {
                Photo photo = new Photo();
                photo.setPhotoPath(imagePath);
                photo.setPhotoName(imageName);
                photo.setInstruction(instruction);
                photoRepository.save(photo);
            }
        }
    }

    @Override
    public void deletePhotoToInstruction(int recipeId, int instructionId, int photoId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe != null) {
            Instruction instruction = instructionRepository.findByInstructionIdAndRecipe(instructionId, recipe);
            if (instruction != null) {
                Photo photo = photoRepository.findById(photoId).orElse(null);
                if (photo != null && photo.getInstruction().getInstructionId() == instructionId) {
                    photoRepository.delete(photo);
                }
            }
        }
    }
}
