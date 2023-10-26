package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.model.CuisineCategory;

import java.util.List;

public interface CuisineCategoryRepository {
    void save(CuisineCategory cuisineCategory);
    List<CuisineCategory> findAllCuisineCategories();
    CuisineCategory findCuisineById(int id);
    CuisineCategory findCuisineByName(String name);

}
