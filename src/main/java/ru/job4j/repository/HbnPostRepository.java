package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class HbnPostRepository implements PostRepository {
    private final CrudRepository repository;

    @Override
    public Post add(Post post) {
        repository.run(session -> session.persist(post));
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
}
