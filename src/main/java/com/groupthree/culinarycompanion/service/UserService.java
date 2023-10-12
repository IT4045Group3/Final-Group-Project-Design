package com.groupthree.culinarycompanion.service;

import com.groupthree.culinarycompanion.dao.IUserDAO;
import com.groupthree.culinarycompanion.dto.UserDTO;
import com.groupthree.culinarycompanion.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private IUserDAO userDAO;

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
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false; // Login is invalid
    }

    private UserDTO mapModelToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        // Map other attributes as needed
        return dto;
    }

    private User mapDTOToModel(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        // Map other attributes as needed
        return user;
    }
}
