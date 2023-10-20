package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.dto.CuisineCategoryDTO;
import com.groupthree.culinarycompanion.model.CuisineCategory;

import java.util.List;

public interface ICuisineCategoryDAO {
    void save(CuisineCategory cuisineCategory);
    List<CuisineCategory> findAllCuisineCategories();
    CuisineCategory findCuisineById(int id);
    CuisineCategory findCuisineByName(String name);

}
