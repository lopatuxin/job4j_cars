package ru.job4j.repository;

import ru.job4j.model.Car;

import java.util.Optional;

public interface CarRepository {

    Car add(Car car);
    boolean update(Car car);
    boolean delete(int id);
    Optional<Car> findById(int id);
}
