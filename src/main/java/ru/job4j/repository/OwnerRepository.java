package ru.job4j.repository;

import ru.job4j.model.Owner;

import java.util.Optional;

public interface OwnerRepository {

    Owner add(Owner owner);
    boolean update(Owner owner);
    boolean delete(int id);
    Optional<Owner> findById(int id);
}
