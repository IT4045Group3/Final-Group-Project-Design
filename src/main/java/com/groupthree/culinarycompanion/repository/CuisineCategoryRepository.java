package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineCategoryRepository extends JpaRepository<Cuisine,Integer> {
    Cuisine findByName(String name);

}
