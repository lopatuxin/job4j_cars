package ru.job4j.service;

import ru.job4j.dto.FileDto;
import ru.job4j.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post add(Post post, FileDto[] images);
    List<Post> findByLastDay();
    List<Post> findByMarkAuto(String name);
    List<Post> findByWithPhoto();
    List<Post> findAll();
    Optional<Post> findById(int id);
    boolean update(Post post);
    boolean updateStatus(Post post);
}
