package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.config.HbmTestConfig;
import ru.job4j.model.Owner;
import ru.job4j.model.User;

import static org.assertj.core.api.Assertions.*;

class OwnerRepositoryTest {
    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final OwnerRepository ownerRepository = new OwnerRepository(crudRepository);
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
    public void whenAddOwner() {
        User user = new User(1, "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        ownerRepository.add(owner);
        assertThat(owner).isEqualTo(ownerRepository.findById(owner.getId()).get());
    }

    @Test
    public void whenDeleteOwner() {
        User user = new User(1, "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        ownerRepository.add(owner);
        boolean result = ownerRepository.delete(owner.getId());
        assertThat(result).isTrue();
    }

    @Test
    public void whenUpdateOwner() {
        User user = new User(1, "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        ownerRepository.add(owner);
        Owner updatingOwner = new Owner(owner.getId(), "test2", user);
        ownerRepository.update(updatingOwner);
        assertThat(updatingOwner.getName()).isEqualTo(ownerRepository.findById(owner.getId()).get().getName());
    }
}