package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.CuisineCategory;

import java.util.List;

public interface CuisineCategoryRepository {
    void save(CuisineCategory cuisineCategory);
    List<CuisineCategory> findAllCuisineCategories();
    CuisineCategory findCuisineById(int id);
    CuisineCategory findCuisineByName(String name);

}
