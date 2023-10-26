package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.Recipe;
import com.groupthree.culinarycompanion.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User findUserById(int userId);
    User findUserByEmail(String email);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    List<Recipe> getRecipesByUserId(int userId);

}
