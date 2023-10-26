package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.CuisineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineCategoryRepository extends JpaRepository<CuisineCategory,Integer> {
    CuisineCategory findByName(String name);

}
