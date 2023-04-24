package ru.job4j.service;

import ru.job4j.model.Car;

import java.util.Optional;

public interface CarService {

    Car add(Car car);
    boolean update(Car car);
    boolean delete(int id);
    Optional<Car> findById(int id);
}
