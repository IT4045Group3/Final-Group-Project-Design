package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dto.RecipeDTO;
import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    void createUser(UserDTO userDTO);
    void updateUser(int userId, UserDTO userDTO);
    void deleteUser(int userId);
    UserDTO findUserById(int userId);
    UserDTO findUserByEmail(String email);
    boolean isValidLogin(String email, String password);
    String saveImage(MultipartFile file);
    User mapDTOToModel(UserDTO dto);
    UserDTO mapModelToDTO(User user);
}

