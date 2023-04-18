package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.config.HbmTestConfig;
import ru.job4j.model.Engine;

import static org.assertj.core.api.Assertions.*;

class EngineRepositoryTest {
    private static SessionFactory sf;
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);

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
        Engine result = engineRepository.add(engine);
        assertThat(engine).isEqualTo(result);
    }

    @Test
    public void whenUpdateEngine() {
        Engine engine = new Engine(1, "test");
        engineRepository.add(engine);
        Engine updatingEngine = new Engine(engine.getId(), "test1");
        engineRepository.update(updatingEngine);
        assertThat(updatingEngine.getName())
                .isEqualTo(engineRepository.findById(updatingEngine.getId()).get().getName());
    }

    @Test
    public void whenDeleteEngine() {
        Engine engine = new Engine(1, "test");
        engineRepository.add(engine);
        boolean result = engineRepository.delete(engine.getId());
        assertThat(result).isTrue();
    }
}