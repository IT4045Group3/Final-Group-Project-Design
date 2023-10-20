package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dao.IUserDAO;
import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private IUserDAO userDAO;

    @Autowired
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    private Environment environment;

    @Override
    public void createUser(UserDTO userDTO) {
        User user = mapDTOToModel(userDTO);
        userDAO.createUser(user);
    }

    @Override
    public void updateUser(int userId, UserDTO userDTO) {
        User user = mapDTOToModel(userDTO);
        user.setUserId(userId);
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    @Override
    public UserDTO findUserById(int userId) {
        User user = userDAO.findUserById(userId);
        return mapModelToDTO(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userDAO.findUserByEmail(email);
        return mapModelToDTO(user);
    }

    public boolean isValidLogin(String email, String password) {
        User user = userDAO.findUserByEmail(email);

        if (user == null) {
            return false;
        }
        return isPasswordValid(user, password);
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

    @Override
    public UserDTO mapModelToDTO(User user) {
        if (user != null) {
            UserDTO dto = new UserDTO();
            dto.setUserId(user.getUserId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            return dto;
        } else {
            return null;
        }
    }


    @Override
    public User mapDTOToModel(UserDTO dto) {
        if (dto != null) {
            User user = new User();
            user.setUserId(dto.getUserId());
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            return user;
        } else {
            return null;
        }

    }

    private boolean isPasswordValid(User user, String password) {
        return user.getPassword().equals(password);
    }

}
