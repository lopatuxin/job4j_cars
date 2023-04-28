package ru.job4j.repository;

import ru.job4j.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post add(Post post);
    List<Post> findByLastDay();
    List<Post> findByMarkAuto(String name);
    List<Post> findByWithPhoto();
    List<Post> findAll();
    Optional<Post> findById(int id);
    boolean update(Post post);
    boolean updateStatus(Post post);
}
