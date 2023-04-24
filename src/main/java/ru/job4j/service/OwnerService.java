package ru.job4j.service;

import ru.job4j.model.Owner;

import java.util.Optional;

public interface OwnerService {

    Owner add(Owner owner);
    boolean update(Owner owner);
    boolean delete(int id);
    Optional<Owner> findById(int id);
}
