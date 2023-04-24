package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Owner;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnOwnerRepository implements OwnerRepository {
    private final CrudRepository repository;

    @Override
    public Owner add(Owner owner) {
        repository.run(session -> session.save(owner));
        return owner;
    }

    @Override
    public boolean update(Owner owner) {

        return repository.booleanRun(session -> {
            session.merge(owner);
            return true;
        });
    }

    @Override
    public boolean delete(int id) {
        return repository.booleanRun(
                "delete from Owner where id = :fId",
                Map.of("fId", id)
        );
    }

    @Override
    public Optional<Owner> findById(int id) {
        return repository.optional(
                "from Owner where id = :fId", Owner.class,
                Map.of("fId", id)
        );
    }
}
