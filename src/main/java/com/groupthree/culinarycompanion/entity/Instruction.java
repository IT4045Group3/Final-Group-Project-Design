package com.groupthree.culinarycompanion.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Instruction {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int instructionId;
    private int stepNumber;
    private String description;
    private String videoURL;
    @Getter
    @OneToMany(mappedBy = "instruction")
    private List<Photo> photos;
    @ManyToOne
    private Recipe recipe;

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
