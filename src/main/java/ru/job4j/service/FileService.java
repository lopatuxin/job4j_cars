package ru.job4j.service;

import ru.job4j.dto.FileDto;
import ru.job4j.model.File;
import ru.job4j.model.Post;

import java.util.Optional;

public interface FileService {

    File save(FileDto fileDto, Post post);

    Optional<FileDto> getFileById(int id);

    boolean deleteById(int id);

}
