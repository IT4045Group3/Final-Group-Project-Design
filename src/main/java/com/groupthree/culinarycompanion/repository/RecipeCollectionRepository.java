package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.RecipeCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecipeCollectionRepository extends JpaRepository<RecipeCollection, Integer> {
    List<RecipeCollection> findByUserId(int userId);

}

