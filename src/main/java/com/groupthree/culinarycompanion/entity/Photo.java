package com.groupthree.culinarycompanion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int photoId;
    private String photoName;
    private String photoPath;
    @ManyToOne
    private Recipe recipe;
    @ManyToOne
    private CuisineCategory cuisineCategory;
    @ManyToOne
    private Instruction instruction;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
