package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Engine;
import ru.job4j.repository.EngineRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleEngineService implements EngineService {
    private final EngineRepository repository;

    @Override
    public Engine add(Engine engine) {
        return repository.add(engine);
    }

    @Override
    public boolean update(Engine engine) {
        return repository.update(engine);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Optional<Engine> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Engine> findAll() {
        return repository.findAll();
    }
}
