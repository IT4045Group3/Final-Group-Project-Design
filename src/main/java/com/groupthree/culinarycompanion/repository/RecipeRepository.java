package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.Instruction;
import com.groupthree.culinarycompanion.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByUserUserId(int userId);
    Recipe findByName(String name);
    List<Recipe> findByNameContaining(String keyword);
    List<Recipe> findByCuisineId(int cuisineId);

}

