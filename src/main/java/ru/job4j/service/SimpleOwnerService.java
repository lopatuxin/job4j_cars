package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Owner;
import ru.job4j.repository.OwnerRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleOwnerService implements OwnerService {

    private final OwnerRepository repository;

    @Override
    public Owner add(Owner owner) {
        return repository.add(owner);
    }

    @Override
    public boolean update(Owner owner) {
        return repository.update(owner);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Optional<Owner> findById(int id) {
        return repository.findById(id);
    }
}
