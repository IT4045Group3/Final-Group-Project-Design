package com.groupthree.culinarycompanion.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class UserDTO {
    private int userId;
    private String username;
    private String email;
    private String password;
    private List<RecipeDTO> recipes = new ArrayList<>();

}
