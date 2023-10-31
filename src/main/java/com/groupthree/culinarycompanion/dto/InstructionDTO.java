package com.groupthree.culinarycompanion.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class InstructionDTO {
    private int instructionId;
    private int stepNumber;
    private String description;
    private String videoURL;
    private List<PhotoDTO> photos = new ArrayList<>();
}

