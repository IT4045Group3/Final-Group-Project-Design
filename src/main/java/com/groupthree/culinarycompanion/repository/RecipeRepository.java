package com.groupthree.culinarycompanion.repository;

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
    List<Recipe> findByCuisineCuisineIdInAndTypeInAndDifficultyInAndIngredientsIngredientIdInAndNameContainingOrderByDifficultyDescNameAsc
            (List<Integer> cuisine_id, List<Recipe.RecipeType> type, List<Recipe.Difficulty> difficulty, List<Integer> ingredientId, String name);
    List<Recipe> findByCuisineCuisineIdInAndTypeInAndDifficultyInAndIngredientsIngredientIdInAndNameContainingOrderByDifficultyAscNameAsc
            (List<Integer> cuisineId, List<Recipe.RecipeType> type, List<Recipe.Difficulty> difficulty, List<Integer> ingredientId, String name);

}

