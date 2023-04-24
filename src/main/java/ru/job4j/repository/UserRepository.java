package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class UserRepository {
    private final CrudRepository repository;

    public User create(User user) {
        repository.run(session -> session.save(user));
        return user;
    }

    public boolean update(User user) {

        return repository.booleanRun(session -> {
            session.merge(user);
            return true;
        });
    }

    public boolean delete(int userId) {
        return repository.booleanRun(
                "delete from User where id = :fId",
                Map.of("fId", userId)
        );
    }

    public List<User> findAllOrderById() {
        return repository.query("from User order by id asc", User.class);
    }

    public Optional<User> findById(int id) {
        return repository.optional(
                "from User where id = :fId", User.class,
                Map.of("fId", id)
        );
    }

    public List<User> findByLikeLogin(String key) {
        return repository.query(
                "from User where login like :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    public Optional<User> findByLogin(String login) {
        return repository.optional(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }
}
