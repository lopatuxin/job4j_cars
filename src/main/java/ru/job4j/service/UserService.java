package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User create(User user) {
        repository.create(user);
        return user;
    }

    public boolean update(User user) {

        return repository.update(user);
    }

    public boolean delete(int userId) {
        return repository.delete(userId);
    }

    public List<User> findAllOrderById() {
        return repository.findAllOrderById();
    }

    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    public List<User> findByLikeLogin(String key) {
        return repository.findByLikeLogin(key);
    }

    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
