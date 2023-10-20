package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.CuisineCategoryDTO;
import com.groupthree.culinarycompanion.model.CuisineCategory;

import java.util.List;

public interface ICuisineCategoryService {

    List<CuisineCategory> getAllCuisineCategories();
    void addCuisineCategory(CuisineCategoryDTO cuisineCategory);
    CuisineCategory getCuisineByName(String name);
    CuisineCategory getCuisineById(int id);
    CuisineCategoryDTO mapModelToDTO(CuisineCategory cuisineCategory);
    CuisineCategory mapDTOToModel(CuisineCategoryDTO cuisineCategoryDTO);

}
