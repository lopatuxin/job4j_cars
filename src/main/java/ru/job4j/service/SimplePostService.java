package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.dto.FileDto;
import ru.job4j.model.File;
import ru.job4j.model.Post;
import ru.job4j.repository.PostRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {
    private final PostRepository postRepository;
    private final FileService fileService;

    private void saveFiles(Post post, FileDto[] files) {
        List<File> list = Arrays.stream(files)
                .map(fileDto -> fileService.save(fileDto, post))
                .toList();
        post.setFiles(list);
    }

    @Override
    public Post add(Post post, FileDto[] images) {
        saveFiles(post, images);
        return postRepository.add(post);
    }

    public List<Post> findByLastDay() {
        return postRepository.findByLastDay();
    }

    public List<Post> findByMarkAuto(String name) {
        return postRepository.findByMarkAuto(name);
    }

    public List<Post> findByWithPhoto() {
        return postRepository.findByWithPhoto();
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public boolean update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public boolean updateStatus(Post post) {
        return postRepository.updateStatus(post);
    }
}
