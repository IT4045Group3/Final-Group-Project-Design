package com.groupthree.culinarycompanion.service.impl;

import com.groupthree.culinarycompanion.repository.UserRepository;
import com.groupthree.culinarycompanion.entity.User;
import com.groupthree.culinarycompanion.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userDAO;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(int userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser != null) {
            if (user.getUsername() != null && !existingUser.getUsername().equals(user.getUsername())) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getEmail() != null && !existingUser.getEmail().equals(user.getEmail())) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null && !existingUser.getPassword().equals(user.getPassword())) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getRecipes() != null && !existingUser.getRecipes().equals(user.getRecipes())) {
                existingUser.setRecipes(user.getRecipes());
            }
            userRepository.save(existingUser);
        }
    }


    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isValidLogin(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public String saveImage(MultipartFile file) {
        try {
            String rootPath = System.getProperty("user.dir");

            String originalFileName = file.getOriginalFilename();

            String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

            String filePath = rootPath + "/upload/" + uniqueFileName;
            File dest = new File(filePath);

            file.transferTo(dest);

            return "/upload/" + uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
