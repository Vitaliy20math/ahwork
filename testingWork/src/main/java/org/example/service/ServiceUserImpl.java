package org.example.service;

import org.example.dao.DaoCar;
import org.example.dao.DaoCarImpl;
import org.example.dao.DaoUser;
import org.example.dao.DaoUserImpl;
import org.example.model.User;

import java.util.List;

public class ServiceUserImpl implements ServiceUser {
    DaoUser dao = new DaoUserImpl();

    @Override
    public void createUser(User user) {
        dao.createUser(user);
    }

    @Override
    public User findUserById(int id) {
        return dao.findUserById(id);
    }

    @Override
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Override
    public void deleteUser(User user) {
        dao.deleteUser(user);
    }

    @Override
    public void cleanTable() {
        dao.cleanTable();
    }
}
