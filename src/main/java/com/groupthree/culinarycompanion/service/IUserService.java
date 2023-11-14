package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    void createUser(User user);
    void updateUser(int userId, User user);
    void deleteUser(int userId);
    User findUserById(int userId);
    User findUserByEmail(String email);
    boolean isValidLogin(String email, String password);
    String saveImage(MultipartFile file);
    void loginUser(HttpSession session, HttpServletResponse response, String email);
    boolean validateRegistration(Model model, String email, boolean agree);
    void handleSuccessfulRegistration(HttpSession session, User user);
}

