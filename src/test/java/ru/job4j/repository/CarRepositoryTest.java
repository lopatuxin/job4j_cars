package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void cleanDb() {
        Transaction tx = null;
        try (Session session = sf.openSession()) {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM car").executeUpdate();
            session.createQuery("DELETE FROM auto_user").executeUpdate();
            session.createQuery("DELETE FROM engine").executeUpdate();
            session.createQuery("DELETE FROM owners").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    /*
    @Test
    public void whenAddCarThenFindId() {
        Engine engine = new Engine(1, "test");
        engineRepository.add(engine);
        User user = new User(1, "test", "test");
        userRepository.create(user);
        Owner owner = new Owner(1, "test", user);
        ownerRepository.add(owner);
        Car car = new Car(1, "test", engine, Set.of(owner));
        Car result = carRepository.add(car);
        assertThat(car).isEqualTo(result);
    }

     */
}