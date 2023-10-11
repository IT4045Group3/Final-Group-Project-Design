package com.groupthree.culinarycompanion.dao;

public interface IUserDAO {
    User findUserById(long userId);
    User findUserByEmail(String email);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(long userId);
}
