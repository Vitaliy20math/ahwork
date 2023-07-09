package org.example.dao;

import org.example.model.Car;
import org.example.util.Util;

import java.sql.*;

public class DaoCarImpl implements DaoCar {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createCar(Car car) {
        try (Connection connection = DriverManager.getConnection(Util.DB_URL, Util.DB_LOGIN, Util.DB_PASSWORD)) {
            String sqlCommand = "INSERT INTO cars (carbrand, login_for_car) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1, car.getCarBrand());
            statement.setInt(2, car.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanTable() {
        try (Connection connection = DriverManager.getConnection(Util.DB_URL, Util.DB_LOGIN, Util.DB_PASSWORD))
        {
            Statement statement = connection.createStatement();
            String sqlCommand = "delete from cars";
            statement.executeUpdate(sqlCommand);

            String sqlCommandForResetAutoIncrement = "ALTER SEQUENCE cars_id_seq RESTART WITH 1";
            Statement statementForResetAutoIncrement = connection.createStatement();
            statementForResetAutoIncrement.execute(sqlCommandForResetAutoIncrement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
