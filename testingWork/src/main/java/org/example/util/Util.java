package org.example.util;

import org.example.model.Car;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * метод createRelations позволяет автоматически наладить связь m-to-m в связывающей таблице без внесения
 * данных ручным путем.
 *
 * По-скольку каждый User хранит в себе лист с машинами, то мы может просмотреть, какая машина
 * лежит в его списке и сравнить с имеющейся машиной в таблице машин.
 *
 * Если одна из его машин равна той, что лежит в таблице машин, тогда в связывающую таблицу будут
 * вставляться значения:
 *
 * (id юзера, чей лист машин проверяется) <-> (id машины, которая нашлась в списке машин пользователя)
 *
 * Для данной цели написан метод getUserByUniqueLogin и getCarByUniqueLogin, который возвращает id по уникальному
 * логину пользователя, который был введет специально для этого, при этом он уникальный
 * для каждого юзера
 *
 * Кроме всего, решил сделать связную таблицу своими руками,
 * не прибегая к FK и привязке к PK соответствующих таблиц, теперь при
 * любых изменениях входных параметров юзера, значения в таблице
 * relations будут меняться автоматически
 *
 */
public class Util {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String DB_LOGIN = "postgres";
    public static final String DB_PASSWORD = "password";

   public static void createRelations(User user) {

       try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD)) {
           String sqlCommandForCars = "select * from cars";
           Statement statementForCars = connection.createStatement();
           ResultSet resultSetForCars = statementForCars.executeQuery(sqlCommandForCars);
           List<String> listForCar = new ArrayList<>();
           String carBrandName;
           while (resultSetForCars.next()) {
               carBrandName = resultSetForCars.getString("carbrand");
               for (Car car : user.getCars()) {
                   if (car.getCarBrand().equals(carBrandName)) {
                       String sqlCommandForEnterRelationsBetweenUsersAndCars = "insert into relations (id_user, id_car) values (?, ?)";
                       PreparedStatement preparedStatement = connection.prepareStatement(sqlCommandForEnterRelationsBetweenUsersAndCars);
                       preparedStatement.setInt(1, getUserByUniqueLogin(user.getLogin()));
                       preparedStatement.setInt(2, getCarByUniqueLogin(car.getLogin()));
                       preparedStatement.executeUpdate();
                   }
               }

           }

       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

   private static int getUserByUniqueLogin(int login) {
       System.out.println("login = " + login);
       int id = 0;
       try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD)) {
           String sqlCommand = "select id FROM user_table where login=?";
           PreparedStatement statement = connection.prepareStatement(sqlCommand);
           statement.setInt(1, login);
           ResultSet set = statement.executeQuery();

           while (set.next()) {
               id = set.getInt("id");
               System.out.println(id);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return id;
   }

   private static int getCarByUniqueLogin(int login) {
       System.out.println("login = " + login);
       int id = 0;
       try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD)) {
           String sqlCommand = "select id FROM cars where login_for_car=?";
           PreparedStatement statement = connection.prepareStatement(sqlCommand);
           statement.setInt(1, login);
           ResultSet set = statement.executeQuery();
           while (set.next()) {
               id = set.getInt("id");
               System.out.println("id = " + id);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return id;
   }
}
