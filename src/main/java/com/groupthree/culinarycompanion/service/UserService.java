package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dao.IUserDAO;
import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private IUserDAO userDAO;

    @Autowired
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

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

    private boolean isPasswordValid(User user, String password) {
        return user.getPassword().equals(password);
    }

    private UserDTO mapModelToDTO(User user) {
        if (user != null) {
            UserDTO dto = new UserDTO();
            dto.setUserId(user.getUserId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            // Map other attributes as needed
            return dto;
        } else {
            return null;
        }
    }


    private User mapDTOToModel(UserDTO dto) {
        if (dto != null) {
            User user = new User();
            user.setUserId(dto.getUserId());
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            // Map other attributes as needed
            return user;
        } else {
            return null;
        }

    }
}
