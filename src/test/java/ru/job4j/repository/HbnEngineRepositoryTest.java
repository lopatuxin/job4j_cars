package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.configuration.HbmTestConfig;
import ru.job4j.model.Engine;

import static org.assertj.core.api.Assertions.*;

class HbnEngineRepositoryTest {
    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final HbnEngineRepository hbnEngineRepository = new HbnEngineRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HbmTestConfig().getSessionFactory();
    }

    @AfterAll
    public static void close() {
        sf.close();
    }

    @Test
    public void whenAddEngine() {
        Engine engine = new Engine(1, "test");
        Engine result = hbnEngineRepository.add(engine);
        assertThat(engine).isEqualTo(result);
    }

    @Test
    public void whenUpdateEngine() {
        Engine engine = new Engine(1, "test");
        hbnEngineRepository.add(engine);
        Engine updatingEngine = new Engine(engine.getId(), "test1");
        hbnEngineRepository.update(updatingEngine);
        assertThat(updatingEngine.getName())
                .isEqualTo(hbnEngineRepository.findById(updatingEngine.getId()).get().getName());
    }

    @Test
    public void whenDeleteEngine() {
        Engine engine = new Engine(1, "test");
        hbnEngineRepository.add(engine);
        boolean result = hbnEngineRepository.delete(engine.getId());
        assertThat(result).isTrue();
    }
}