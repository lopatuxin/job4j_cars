package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.configuration.HbmTestConfig;
import ru.job4j.model.Car;
import ru.job4j.model.Engine;
import ru.job4j.model.Owner;
import ru.job4j.model.User;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class HbnCarRepositoryTest {
    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final HbnCarRepository hbnCarRepository = new HbnCarRepository(crudRepository);
    private final HbnEngineRepository hbnEngineRepository = new HbnEngineRepository(crudRepository);
    private final HbnOwnerRepository hbnOwnerRepository = new HbnOwnerRepository(crudRepository);
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
    public void whenAddCarThenFindId() {
        Engine engine = new Engine(1, "test");
        hbnEngineRepository.add(engine);
        User user = new User(1, "test", "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        hbnOwnerRepository.add(owner);
        Car result = hbnCarRepository.add(new Car(1, "test", "test", engine, Set.of(owner)));
        assertThat(result).isEqualTo(hbnCarRepository.findById(result.getId()).get());
    }

    @Test
    public void whenDeleteCar() {
        Engine engine = new Engine(1, "test");
        hbnEngineRepository.add(engine);
        User user = new User(1, "test", "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        hbnOwnerRepository.add(owner);
        Car car = hbnCarRepository.add(new Car(1, "test", "test", engine, Set.of(owner)));
        boolean result = hbnCarRepository.delete(car.getId());
        assertThat(result).isTrue();
    }

    @Test
    public void whenUpdateCar() {
        Engine engine = new Engine(1, "test");
        hbnEngineRepository.add(engine);
        User user = new User(1, "test", "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        hbnOwnerRepository.add(owner);
        hbnCarRepository.add(new Car(1, "test", "test", engine, Set.of(owner)));
        Car updateCar = new Car(1, "test2", "test2", engine, Set.of(owner));
        boolean result = hbnCarRepository.update(updateCar);
        assertThat(result).isTrue();
    }

}