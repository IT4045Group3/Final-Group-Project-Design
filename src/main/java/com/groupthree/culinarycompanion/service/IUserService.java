package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    void createUser(UserDTO userDTO);
    void updateUser(int userId, UserDTO userDTO);
    void deleteUser(int userId);
    UserDTO findUserById(int userId);
    UserDTO findUserByEmail(String email);
    boolean isValidLogin(String email, String password);

    String saveImage(MultipartFile file);
}

