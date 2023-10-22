package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.InstructionDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Instruction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IInstructionService {
    InstructionDTO addInstructionToRecipe(int recipeId, InstructionDTO newInstruction);
    void updateInstructionInRecipe(int recipeId, int instructionId, InstructionDTO updatedInstruction);
    void removeInstructionFromRecipe(int recipeId, int instructionId);
    InstructionDTO getInstructionById(int recipeId, int instructionId);
    void addPhotoInInstruction(int recipeId, int instructionId,String imagePath);
    void deletePhotoToInstruction(int recipeId, int instructionId, int photoId);
    Instruction mapDTOToModel(InstructionDTO dto);
    InstructionDTO mapModelToDTO(Instruction instruction);
}
