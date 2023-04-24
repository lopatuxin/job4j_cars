package ru.job4j.service;

import ru.job4j.dto.FileDto;
import ru.job4j.model.Post;

import java.util.List;

public interface PostService {

    Post add(Post post, FileDto[] images);
    List<Post> findByLastDay();
    List<Post> findByMarkAuto(String name);
    List<Post> findByWithPhoto();
}
