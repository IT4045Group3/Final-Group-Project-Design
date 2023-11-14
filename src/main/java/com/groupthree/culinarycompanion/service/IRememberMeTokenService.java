package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Date;

public interface IRememberMeTokenService {
    void saveRememberMeToken(int userId, String token, Date expirationDate);
    String generateRememberMeToken(User user);
    Date calculateExpirationDate();
    boolean isValidRememberMeToken(String token);
    int getUserIdFromRememberMeToken(String token);
    boolean isTokenExpired(Date expirationDate);
    void deleteRememberMeToken(String token);
    void checkRememberMeToken(HttpServletRequest request, HttpSession session);
    void clearRememberMeToken(HttpServletRequest request, HttpServletResponse response);
    void clearSessionAttributes(HttpSession session);
    void rememberUser(HttpServletResponse response, String email);
}
