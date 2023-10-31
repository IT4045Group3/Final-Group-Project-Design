package com.groupthree.culinarycompanion.dto;

import lombok.Data;

import java.util.List;

public @Data class CuisineCategoryDTO {

    private int id;
    private String name;
    private List<PhotoDTO> photos;

}
