package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUserId(int id);
}
