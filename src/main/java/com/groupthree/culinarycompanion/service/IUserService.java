package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.UserDTO;

public interface IUserService {
    void createUser(UserDTO userDTO);
    void updateUser(int userId, UserDTO userDTO);
    void deleteUser(int userId);
    UserDTO findUserById(int userId);
    UserDTO findUserByEmail(String email);
}

