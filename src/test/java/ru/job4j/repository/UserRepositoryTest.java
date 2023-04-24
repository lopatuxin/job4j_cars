package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.configuration.HbmTestConfig;
import ru.job4j.model.User;

import static org.assertj.core.api.Assertions.*;

class UserRepositoryTest {
    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final UserRepository userRepository = new UserRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HbmTestConfig().getSessionFactory();
    }

    @AfterAll
    public static void close() {
        sf.close();
    }

    @Test
    public void whenCreateUser() {
        User user = new User(1, "test", "test");
        userRepository.create(user);
        assertThat(user).isEqualTo(userRepository.findById(user.getId()).get());
    }

    @Test
    public void whenUpdateUser() {
        User user = new User(1, "test", "test");
        userRepository.create(user);
        User updatingUser = new User(user.getId(), "test2", "test2");
        userRepository.update(updatingUser);
        assertThat(updatingUser.getLogin()).isEqualTo(userRepository.findById(user.getId()).get().getLogin());
    }

    @Test
    public void whenDeleteUser() {
        User user = new User(1, "test", "test");
        userRepository.create(user);
        boolean result = userRepository.delete(user.getId());
        assertThat(result).isTrue();
    }
}