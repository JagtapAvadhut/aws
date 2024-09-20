package com.aws.services.impl;

import com.aws.entities.User;
import com.aws.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(UUID.randomUUID(), "john", "password1"));
        users.add(new User(UUID.randomUUID(), "jane", "password2"));
        users.add(new User(UUID.randomUUID(), "mike", "password3"));
        users.add(new User(UUID.randomUUID(), "alex", "password4"));
        users.add(new User(UUID.randomUUID(), "sarah", "password5"));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(UUID id) {
        Optional<User> user = users.stream().filter(u -> u.getUuid().equals(id)).findFirst();
        return user.orElse(null);
    }

    @Override
    public User createUser(User user) {
        user.setUuid(UUID.randomUUID());  // Auto-generate UUID for the new user
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(UUID id, User updatedUser) {
        for (User user : users) {
            if (user.getUuid().equals(id)) {
                user.setUName(updatedUser.getUName());
                user.setUPassword(updatedUser.getUPassword());
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(UUID id) {
        return users.removeIf(user -> user.getUuid().equals(id));
    }
}
