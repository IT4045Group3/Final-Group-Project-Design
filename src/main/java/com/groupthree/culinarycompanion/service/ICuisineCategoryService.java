package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.entity.Cuisine;

import java.util.List;

public interface ICuisineCategoryService {

    List<Cuisine> getAllCuisineCategories();
    Cuisine addCuisineCategory(Cuisine cuisine);
    Cuisine getCuisineByName(String name);
    Cuisine getCuisineById(int id);
    void addPhotoInCategory(int cuisineCategoryId, String imagePath, String imageName);

}
