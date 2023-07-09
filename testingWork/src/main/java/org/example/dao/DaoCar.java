package org.example.dao;

import org.example.model.Car;

public interface DaoCar {
    void createCar(Car car);
    void cleanTable();
}
