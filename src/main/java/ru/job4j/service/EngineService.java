package ru.job4j.service;

import ru.job4j.model.Engine;

import java.util.List;
import java.util.Optional;

public interface EngineService {
    Engine add(Engine engine);
    boolean update(Engine engine);
    boolean delete(int id);
    Optional<Engine> findById(int id);
    List<Engine> findAll();
}
