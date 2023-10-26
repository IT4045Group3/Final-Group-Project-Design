package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.model.Recipe;
import com.groupthree.culinarycompanion.model.User;

import java.util.List;

public interface UserRepository {
    User findUserById(int userId);
    User findUserByEmail(String email);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    List<Recipe> getRecipesByUserId(int userId);

}
