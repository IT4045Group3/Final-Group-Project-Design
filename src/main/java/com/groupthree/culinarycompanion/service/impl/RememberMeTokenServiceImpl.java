package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.entity.RememberMeToken;
import com.groupthree.culinarycompanion.entity.User;
import com.groupthree.culinarycompanion.repository.RememberMeTokenRepository;
import com.groupthree.culinarycompanion.repository.UserRepository;
import com.groupthree.culinarycompanion.service.IRememberMeTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class RememberMeTokenServiceImpl implements IRememberMeTokenService {

    @Autowired
    private RememberMeTokenRepository rememberMeTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveRememberMeToken(int userId, String token, Date expirationDate) {
        RememberMeToken rememberMeToken = new RememberMeToken();
        rememberMeToken.setUserId(userId);
        rememberMeToken.setToken(token);
        rememberMeToken.setExpirationDate(expirationDate);

        rememberMeTokenRepository.save(rememberMeToken);
    }

    @Override
    public String generateRememberMeToken(User user) {
        String token = UUID.randomUUID().toString();
        Date expirationDate = calculateExpirationDate();
        saveRememberMeToken(user.getUserId(), token, expirationDate);

        return token;
    }

    @Override
    public Date calculateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        return calendar.getTime();
    }

    @Override
    public boolean isValidRememberMeToken(String token) {
        RememberMeToken rememberMeToken = rememberMeTokenRepository.findByToken(token);

        return rememberMeToken != null && !isTokenExpired(rememberMeToken.getExpirationDate());
    }

    @Override
    public int getUserIdFromRememberMeToken(String token) {
        RememberMeToken rememberMeToken = rememberMeTokenRepository.findByToken(token);

        if (rememberMeToken != null) {
            return rememberMeToken.getUserId();
        }

        return -1;
    }

    @Override
    public boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }

    @Override
    public void deleteRememberMeToken(String token) {
        RememberMeToken rememberMeToken = rememberMeTokenRepository.findByToken(token);
        if (rememberMeToken != null) {
            rememberMeTokenRepository.delete(rememberMeToken);
        }
    }

    @Override
    public void checkRememberMeToken(HttpServletRequest request, HttpSession session) {

        if (session.getAttribute("loggedInUserId") != null) {
            return;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("rememberMeToken")) {
                    String token = cookie.getValue();

                    if (isValidRememberMeToken(token)) {
                        int userId = getUserIdFromRememberMeToken(token);
                        User user = userRepository.findByUserId(userId);
                        if (user != null) {
                            session.setAttribute("loggedInUserName", user.getUsername());
                            session.setAttribute("loggedInUserId", user.getUserId());
                            session.setAttribute("loginSuccessful", "Successful login as " + user.getUsername());

                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public void run() {
                                            session.removeAttribute("loginSuccessful");
                                        }
                                    },
                                    1000
                            );
                        }
                    }
                }
            }
        }
    }

    @Override
    public void clearRememberMeToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("rememberMeToken")) {
                    String token = cookie.getValue();
                    deleteRememberMeToken(token);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    @Override
    public void clearSessionAttributes(HttpSession session) {
        session.removeAttribute("loggedInUserName");
        session.removeAttribute("loginSuccessful");
        session.removeAttribute("loggedInUserId");
    }

    @Override
    public void rememberUser(HttpServletResponse response, String email) {
        String rememberMeToken = generateRememberMeToken(userRepository.findByEmail(email));
        Cookie cookie = new Cookie("rememberMeToken", rememberMeToken);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(cookie);
    }
}
