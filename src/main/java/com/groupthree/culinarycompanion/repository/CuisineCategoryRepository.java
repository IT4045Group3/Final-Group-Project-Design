package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.CuisineCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineCategoryRepository {
    void save(CuisineCategory cuisineCategory);
    List<CuisineCategory> findAllCuisineCategories();
    CuisineCategory findCuisineById(int id);
    CuisineCategory findCuisineByName(String name);

}
