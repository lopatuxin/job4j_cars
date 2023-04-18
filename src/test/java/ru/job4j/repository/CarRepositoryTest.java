package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.config.HbmTestConfig;
import ru.job4j.model.Car;
import ru.job4j.model.Engine;
import ru.job4j.model.Owner;
import ru.job4j.model.User;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class CarRepositoryTest {
    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final CarRepository carRepository = new CarRepository(crudRepository);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);
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
    public void whenAddCarThenFindId() {
        Engine engine = new Engine(1, "test");
        engineRepository.add(engine);
        User user = new User(1, "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        ownerRepository.add(owner);
        Car result = carRepository.add(new Car(1, "test", engine, Set.of(owner)));
        assertThat(result).isEqualTo(carRepository.findById(result.getId()).get());
    }

    @Test
    public void whenDeleteCar() {
        Engine engine = new Engine(1, "test");
        engineRepository.add(engine);
        User user = new User(1, "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        ownerRepository.add(owner);
        Car car = carRepository.add(new Car(1, "test", engine, Set.of(owner)));
        boolean result = carRepository.delete(car.getId());
        assertThat(result).isTrue();
    }

    @Test
    public void whenUpdateCar() {
        Engine engine = new Engine(1, "test");
        engineRepository.add(engine);
        User user = new User(1, "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        ownerRepository.add(owner);
        carRepository.add(new Car(1, "test", engine, Set.of(owner)));
        Car updateCar = new Car(1, "test2", engine, Set.of(owner));
        boolean result = carRepository.update(updateCar);
        assertThat(result).isTrue();
    }

}