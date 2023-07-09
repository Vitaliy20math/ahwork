package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
public class User {
    int id;//id автоинкремент
    String username;//имя
    String surname;//фамилия
    int salary;//зарплата
    String stuff;//должность

    int login;

    List<Car> cars;
    public User() {}

    public User(int id, String username, String surname, int salary, String stuff, List<Car> cars) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.salary = salary;
        this.stuff = stuff;
        this.cars = cars;
    }

    public User(String username, String surname, int salary, String stuff, List<Car> cars, int login) {
        this.username = username;
        this.surname = surname;
        this.salary = salary;
        this.stuff = stuff;
        this.cars = cars;
        this.login = login;
    }

    public User(int id, String username, String surname, int salary, String stuff, int login, List<Car> list) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.salary = salary;
        this.stuff = stuff;
        this.login = login;
        this.cars = list;
    }

    public User(int id, String username, String surname, int salary, String stuff, int login) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.salary = salary;
        this.stuff = stuff;
        this.login = login;
    }

    public User(String username, String surname, int salary, String stuff, int login) {
        this.username = username;
        this.surname = surname;
        this.salary = salary;
        this.stuff = stuff;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuff() {
        return stuff;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setStuff(String stuff) {
        this.stuff = stuff;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return salary == user.salary && Objects.equals(username, user.username) && Objects.equals(surname, user.surname) && Objects.equals(stuff, user.stuff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, surname, salary, stuff);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", stuff='" + stuff + '\'' +
                ", login=" + login +
                ", cars=" + cars +
                '}';
    }
}
