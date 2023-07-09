package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
public class Car {
    int id;
    String carBrand;

    int login;
    List<User> users = new ArrayList<>();

    public Car(String carBrand, int login) {
        this.carBrand = carBrand;
        this.login = login;
    }

    public Car(String nameCar) {
        this.carBrand = nameCar;
    }

    public Car(int id, String carBrand) {
        this.id = id;
        this.carBrand = carBrand;
    }

    public Car() {

    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carBrand, car.carBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carBrand);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carBrand='" + carBrand + '\'' +
                ", users=" + users +
                '}';
    }
}
