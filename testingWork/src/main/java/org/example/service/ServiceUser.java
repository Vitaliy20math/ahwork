package org.example.service;

import org.example.model.User;

import java.util.List;

public interface ServiceUser {
    void createUser(User user);

    User findUserById(int id);
    List<User> getUsers();
    void deleteUser(User user);
    void cleanTable();
}
