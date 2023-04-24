package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Car;
import ru.job4j.repository.CarRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCarService implements CarService {
    private final CarRepository repository;

    @Override
    public Car add(Car car) {
        return repository.add(car);
    }

    @Override
    public boolean update(Car car) {
        return repository.update(car);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Optional<Car> findById(int id) {
        return repository.findById(id);
    }
}
