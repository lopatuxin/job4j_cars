package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Owner;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OwnerRepository {
    private final CrudRepository repository;

    public Owner add(Owner owner) {
        repository.run(session -> session.save(owner));
        return owner;
    }

    public void update(Owner owner) {
        repository.run(session -> session.merge(owner));
    }

    public void delete(int id) {
        repository.run(
                "delete from Owner where id = :fId",
                Map.of("fId", id)
        );
    }

    public Optional<Owner> findById(int id) {
        return repository.optional(
                "from Owner where id = :fId", Owner.class,
                Map.of("fId", id)
        );
    }
}
