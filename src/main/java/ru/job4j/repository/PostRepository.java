package ru.job4j.repository;

import ru.job4j.model.Post;

import java.util.List;

public interface PostRepository {
    Post add(Post post);
    List<Post> findByLastDay();
    List<Post> findByMarkAuto(String name);
    List<Post> findByWithPhoto();
}
