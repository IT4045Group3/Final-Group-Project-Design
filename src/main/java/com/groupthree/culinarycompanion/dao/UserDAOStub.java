package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOStub implements IUserDAO {
    private List<User> userDatabase = new ArrayList<>();
    private int nextUserId = 1;

    @Override
    public User findUserById(int userId) {
        for (User user : userDatabase) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        for (User user : userDatabase) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void createUser(User user) {
        user.setUserId(nextUserId);
        nextUserId++;
        userDatabase.add(user);
    }

    @Override
    public void updateUser(User user) {
        int index = -1;
        for (int i = 0; i < userDatabase.size(); i++) {
            if (userDatabase.get(i).getUserId() == user.getUserId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            userDatabase.set(index, user);
        }
    }

    @Override
    public void deleteUser(int userId) {
        User userToRemove = null;
        for (User user : userDatabase) {
            if (user.getUserId() == userId) {
                userToRemove = user;
                break;
            }
        }
        if (userToRemove != null) {
            userDatabase.remove(userToRemove);
        }
    }

    @PostConstruct
    public void initDefaultUsers() {

        User user1 = new User();
        user1.setUserId(nextUserId++);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password1");
        userDatabase.add(user1);

        User user2 = new User();
        user2.setUserId(nextUserId++);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setPassword("password2");
        userDatabase.add(user2);

    }
}

