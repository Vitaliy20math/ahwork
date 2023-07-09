package org.example.dao;

import org.example.exceptions.UserIsNotException;
import org.example.model.User;
import org.example.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUserImpl implements DaoUser {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createUser(User user) {
        try (Connection connection = DriverManager.getConnection(Util.DB_URL, Util.DB_LOGIN, Util.DB_PASSWORD)) {
            if (connection != null && !connection.isClosed()) {
                // соединение открыто
                System.out.println("Соединение открыто");
            } else {
                // соединение закрыто или равно null
                System.out.println("Соединение закрыто или отсутствует");
            }

            String username = user.getUsername();
            String surname = user.getSurname();
            int salary = user.getSalary();
            String stuff = user.getStuff();
            int login = user.getLogin();
            System.out.println("check point 1: " +  connection.isClosed());
            String sqlCommand = "INSERT INTO user_table (username, surname, salary, stuff, login) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1, username);
            statement.setString(2, surname);
            statement.setInt(3, salary);
            statement.setString(4, stuff);
            statement.setInt(5, login);
            statement.executeUpdate();
            Util.createRelations(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public User findUserById(int id) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(Util.DB_URL, Util.DB_LOGIN, Util.DB_PASSWORD)) {
            String sqlCommand = "SELECT id, username, surname, salary, stuff, login from user_table where id=?";
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int iD;
            String username;
            String surname;
            int salary;
            String stuff;
            int login;
            while (resultSet.next()) {
                iD = resultSet.getInt("id");
                username = resultSet.getString("username");
                surname = resultSet.getString("surname");
                salary = resultSet.getInt("salary");
                stuff = resultSet.getString("stuff");
                login = resultSet.getInt("login");
                user = new User(iD, username, surname, salary, stuff, login);
            }
            return user;
        } catch (SQLException | UserIsNotException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        User user = null;
        try (Connection connection = DriverManager.getConnection(Util.DB_URL, Util.DB_LOGIN, Util.DB_PASSWORD)) {
            String sqlCommand = "SELECT * from user_table";
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            ResultSet resultSet = statement.executeQuery();
            int iD;
            String username;
            String surname;
            int salary;
            String stuff;
            int login;
            while (resultSet.next()) {
                iD = resultSet.getInt("id");
                username = resultSet.getString("username");
                surname = resultSet.getString("surname");
                salary = resultSet.getInt("salary");
                stuff = resultSet.getString("stuff");
                login = resultSet.getInt("login");
                user = new User(iD, username, surname, salary, stuff, login);
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void deleteUser(User user) {
        try (Connection connection = DriverManager.getConnection(Util.DB_URL, Util.DB_LOGIN, Util.DB_PASSWORD)) {
            String sqlCommand = "delete from user_table where (username, surname, salary, stuff)=(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getSalary());
            statement.setString(4, user.getStuff());
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void cleanTable() {
        try (Connection connection = DriverManager.getConnection(Util.DB_URL, Util.DB_LOGIN, Util.DB_PASSWORD))
        {
            Statement statement = connection.createStatement();
            String sqlCommand = "delete from user_table";
            statement.executeUpdate(sqlCommand);

            String sqlCommandForResetAutoIncrement = "ALTER SEQUENCE user_table_id_seq RESTART WITH 1";
            Statement statementForResetAutoIncrement = connection.createStatement();
            statementForResetAutoIncrement.execute(sqlCommandForResetAutoIncrement);

            String sqlCleanRelationsTable = "delete from relations";
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlCleanRelationsTable);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
