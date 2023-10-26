package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.repository.RecipeRepository;
import com.groupthree.culinarycompanion.dto.InstructionDTO;
import com.groupthree.culinarycompanion.dto.PhotoDTO;
import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.model.Instruction;
import com.groupthree.culinarycompanion.model.Photo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructionService implements IInstructionService{

    private RecipeRepository recipeDAO;
    private IRecipeService recipeService;

    public InstructionService(RecipeRepository recipeDAO, IRecipeService recipeService) {
        this.recipeDAO = recipeDAO;
        this.recipeService = recipeService;
    }

    @Override
    public InstructionDTO addInstructionToRecipe(int recipeId, InstructionDTO newInstruction) {
        return mapModelToDTO(recipeDAO.addInstructionToRecipe(recipeId,mapDTOToModel(newInstruction)));
    }

    @Override
    public void updateInstructionInRecipe(int recipeId, int instructionId, InstructionDTO updatedInstruction) {
       recipeDAO.updateInstructionToRecipe(recipeId, instructionId, mapDTOToModel(updatedInstruction));
    }

    @Override
    public void deletePhotoToInstruction(int recipeId, int instructionId, int photoId) {
        recipeDAO.deletePhotoToInstruction(recipeId, instructionId, photoId);
    }


    @Override
    public void removeInstructionFromRecipe(int recipeId, int instructionId) {

        recipeDAO.removeInstructionFromRecipe(recipeId,instructionId);
        RecipeDTO recipe = recipeService.findRecipeById(recipeId);
        recipeService.updateRecipe(recipeId, recipe);
    }

    @Override
    public void addPhotoInInstruction(int recipeId, int instructionId,String imagePath) {
        recipeDAO.addPhotoInInstruction(recipeId, instructionId, imagePath);
    }

    @Override
    public InstructionDTO getInstructionById(int recipeId, int instructionId) {
        RecipeDTO recipe = recipeService.findRecipeById(recipeId);
        if (recipe != null) {
            List<InstructionDTO> instructions = recipe.getInstructions();
            for (InstructionDTO instruction : instructions) {
                if (instruction.getInstructionId() == instructionId) {
                    return instruction;
                }
            }
        }
        return null;
    }

    @Override
    public InstructionDTO mapModelToDTO(Instruction instruction) {
        InstructionDTO dto = new InstructionDTO();
        dto.setInstructionId(instruction.getInstructionId());
        dto.setStepNumber(instruction.getStepNumber());
        dto.setDescription(instruction.getDescription());
        dto.setVideoURL(instruction.getVideoURL());

        List<PhotoDTO> photoDTOs = new ArrayList<>();
        for (Photo photo : instruction.getPhotos()) {
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setPhotoId(photo.getPhotoId());
            photoDTO.setPhotoName(photo.getPhotoName());
            photoDTO.setPhotoPath(photo.getPhotoPath());
            photoDTOs.add(photoDTO);
        }
        dto.setPhotos(photoDTOs);

        return dto;
    }

    @Override
    public Instruction mapDTOToModel(InstructionDTO dto) {
        Instruction instruction = new Instruction();
        instruction.setInstructionId(dto.getInstructionId());
        instruction.setStepNumber(dto.getStepNumber());
        instruction.setDescription(dto.getDescription());
        instruction.setVideoURL(dto.getVideoURL());

        List<Photo> photos = new ArrayList<>();
        for (PhotoDTO photoDTO : dto.getPhotos()) {
            Photo photo = new Photo();
            photo.setPhotoId(photoDTO.getPhotoId());
            photo.setPhotoName(photoDTO.getPhotoName());
            photo.setPhotoPath(photoDTO.getPhotoPath());
            photos.add(photo);
        }
        instruction.setPhotos(photos);

        return instruction;
    }
}
