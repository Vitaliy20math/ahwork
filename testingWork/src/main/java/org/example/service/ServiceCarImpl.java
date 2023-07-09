package org.example.service;

import org.example.dao.DaoCar;
import org.example.dao.DaoCarImpl;
import org.example.model.Car;

public class ServiceCarImpl implements ServiceCar {

    DaoCar dao = new DaoCarImpl();

    @Override
    public void createCar(Car car) {
        dao.createCar(car);
    }

    @Override
    public void cleanTable() {
        dao.cleanTable();
    }
}
