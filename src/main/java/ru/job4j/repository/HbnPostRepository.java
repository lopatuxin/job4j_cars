package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnPostRepository implements PostRepository {
    private final CrudRepository repository;

    @Override
    public Post add(Post post) {
        repository.run(session -> session.save(post));
        return post;
    }

    @Override
    public List<Post> findByLastDay() {
        return repository.query("FROM Post WHERE created >= :fLastDay", Post.class,
                Map.of("fLastDay", LocalDate.now().minusDays(1)));
    }

    @Override
    public List<Post> findByMarkAuto(String name) {
        return repository.query("SELECT p FROM Post p WHERE p.car.name like :fName)", Post.class,
                Map.of("fName", name));
    }

    @Override
    public List<Post> findByWithPhoto() {
        return repository.query("SELECT p FROM Post p WHERE p.files.size > 0", Post.class);
    }

    @Override
    public List<Post> findAll() {
        return repository.query(
                "SELECT DISTINCT i FROM Post i JOIN FETCH i.user"
                        + " JOIN FETCH i.car JOIN FETCH i.files ORDER BY i.id", Post.class);
    }

    @Override
    public Optional<Post> findById(int id) {
        return repository.optional(
                "FROM Post AS p JOIN FETCH p.user JOIN FETCH p.car JOIN FETCH p.files"
                + " WHERE p.id = :fId", Post.class, Map.of("fId", id));
    }

    @Override
    public boolean update(Post post) {
        return repository.booleanRun(session -> {
            session.update(post);
            return true;
        });
    }

    @Override
    public boolean updateStatus(Post post) {
        return repository.booleanRun("UPDATE Post SET status = :fStatus WHERE id = :fId",
                Map.of("fStatus", true, "fId", post.getId()));
    }
}
