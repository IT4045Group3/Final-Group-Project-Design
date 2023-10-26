package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.entity.Instruction;

public interface IInstructionService {
    Instruction addInstructionToRecipe(int recipeId, Instruction newInstruction);
    void updateInstructionInRecipe(int recipeId, int instructionId, Instruction updatedInstruction);
    void removeInstructionFromRecipe(int recipeId, int instructionId);
    Instruction getInstructionById(int recipeId, int instructionId);
    void addPhotoInInstruction(int recipeId, int instructionId,String imagePath, String imageName);
    void deletePhotoToInstruction(int recipeId, int instructionId, int photoId);
}
