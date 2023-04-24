package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.File;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnFileRepository implements FileRepository {

    private final CrudRepository repository;

    @Override
    public File save(File file) {
        repository.run(session -> session.persist(file));
        return file;
    }

    @Override
    public Optional<File> findById(int id) {
        return repository.optional(
                "SELECT File WHERE id = :fId", File.class, Map.of("fId", id)
        );
    }

    @Override
    public boolean deleteById(int id) {
        return repository.booleanRun("DELETE File WHERE id = :fId", Map.of("fId", id));
    }
}
