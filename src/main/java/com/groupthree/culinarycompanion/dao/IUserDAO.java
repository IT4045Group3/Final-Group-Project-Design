package com.groupthree.culinarycompanion.dao;

public interface IUserDAO {
    User findUserById(int userId);
    User findUserByEmail(String email);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}
