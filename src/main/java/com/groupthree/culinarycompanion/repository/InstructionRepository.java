package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.Instruction;
import com.groupthree.culinarycompanion.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Integer> {
    Instruction findByInstructionIdAndRecipe(int id, Recipe recipe);

}
