package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.User;

public interface IUserDAO {
    User findUserById(int userId);
    User findUserByEmail(String email);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}
