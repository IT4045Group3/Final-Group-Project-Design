package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.entity.CuisineCategory;

import java.util.List;

public interface ICuisineCategoryService {

    List<CuisineCategory> getAllCuisineCategories();
    CuisineCategory addCuisineCategory(CuisineCategory cuisineCategory);
    CuisineCategory getCuisineByName(String name);
    CuisineCategory getCuisineById(int id);
    void addPhotoInCategory(int cuisineCategoryId, String imagePath, String imageName);

}
