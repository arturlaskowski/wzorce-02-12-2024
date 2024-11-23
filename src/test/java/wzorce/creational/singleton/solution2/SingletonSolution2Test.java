package wzorce.creational.singleton.solution2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
class SingletonSolution2Test {

    @Autowired
    private DatabaseConnectionManager firstInstance;

    @Autowired
    private DatabaseConnectionManager secondInstance;

    @Test
    void shouldReturnTheSameInstance() {
        assertSame(firstInstance, secondInstance, "Obie instancje powinny być tym samym obiektem");
    }
}