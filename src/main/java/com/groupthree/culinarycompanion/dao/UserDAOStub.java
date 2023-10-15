package com.groupthree.culinarycompanion.dao;

import com.groupthree.culinarycompanion.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of IUserDAO that uses an in-memory database represented by a List of User objects.
 */
@Repository
public class UserDAOStub implements IUserDAO {
    private final List<User> userDatabase = new ArrayList<>();
    private int nextUserId = 1;

    /**
     * Finds user by their unique identifier.
     *
     * @param userId The unique identifier of the user.
     * @return The user with the specified ID.
     * @throws UserNotFoundException if no user with the specified ID is found.
     */
    @Override
    public User findUserById(int userId) {
        for (User user : userDatabase) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        throw new UserNotFoundException("User with ID" + userId + "not found");
    }

    /**
     * Finds a user by their email address.
     *
     * @param email The email address to search for.
     * @return The user with the specified email address.
     * @throws IllegalArgumentException if the email is null or empty.
     * @throws UserNotFoundException    if no user with the specified email is found.
     */
    @Override
    public User findUserByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        for (User user : userDatabase) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        throw new UserNotFoundException("User with ID" + email + "not found");
    }

    /**
     * Creates a new user and adds them to the database.
     *
     * @param user The user to be added.
     * @throws IllegalArgumentException if the user or any of its mandatory properties are null.
     */
    @Override
    public void createUser(User user) {
        validateUser(user);
        user.setUserId(nextUserId);
        nextUserId++;
        userDatabase.add(user);
    }

    /**
     * Updates the details of an existing user.
     *
     * @param user The user with updated details.
     * @throws IllegalArgumentException if the user or any of its mandatory properties are null.
     * @throws UserNotFoundException    if no user with the specified ID is found.
     */
    @Override
    public void updateUser(User user) {
        validateUser(user);
        int index = -1;
        for (int i = 0; i < userDatabase.size(); i++) {
            if (userDatabase.get(i).getUserId() == user.getUserId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            userDatabase.set(index, user);
        } else {
            throw new UserNotFoundException("User with ID" + user.getUserId() + "not found");
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param userId The ID of the user to be deleted.
     * @throws UserNotFoundException if no user with the specified ID is found.
     */
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
        } else {
            throw new UserNotFoundException("User with ID" + userId + "not found");
        }
    }

    /**
     * Initializes the database with default users.
     */
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

    /**
     * Validates the user and its properties are not null.
     *
     * @param user The user to validate.
     * @throws IllegalArgumentException if the user or any of its required properties are null.
     */
    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getEmail() == null || user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User properties cannot be null");
        }
    }

    /**
     * Exception thrown when a user is not found in the database.
     */
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}

