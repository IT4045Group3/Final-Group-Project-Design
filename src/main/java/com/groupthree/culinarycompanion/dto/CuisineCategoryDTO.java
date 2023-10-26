package com.groupthree.culinarycompanion.dto;

import lombok.Data;

import java.util.List;

public @Data class CuisineCategoryDTO {

    private int id;
    private String name;
    private List<PhotoDTO> photos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }
}
