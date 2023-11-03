package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecipeCollectionRepository extends JpaRepository<Collection, Integer> {
    List<Collection> findByUserId(int userId);

}

