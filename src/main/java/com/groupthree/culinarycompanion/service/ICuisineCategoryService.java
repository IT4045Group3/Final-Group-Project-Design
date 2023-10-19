package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.model.CuisineCategory;

import java.util.List;

public interface ICuisineCategoryService {

    List<CuisineCategory> getAllCuisineCategories();
    void addCuisineCategory(CuisineCategory cuisineCategory);
    CuisineCategory getCuisineByName(String name);
    CuisineCategory getCuisineById(int id);

}
