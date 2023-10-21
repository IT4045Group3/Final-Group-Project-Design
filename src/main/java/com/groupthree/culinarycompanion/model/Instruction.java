package com.groupthree.culinarycompanion.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

public class Instruction {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int instructionId;
    private int stepNumber;
    private String description;
    private String videoURL;
    @Getter
    private List<Photo> photos;

    // Getters and setters

    public int getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(int instructionId) {
        this.instructionId = instructionId;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}

