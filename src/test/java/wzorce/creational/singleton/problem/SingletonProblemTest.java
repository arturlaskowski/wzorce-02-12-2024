package wzorce.creational.singleton.problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class SingletonProblemTest {

    @Test
    void shouldReturnTheSameInstance() {
        DatabaseConnectionManager firstInstance = new DatabaseConnectionManager();
        DatabaseConnectionManager secondInstance = new DatabaseConnectionManager();

        assertSame(firstInstance, secondInstance, "Obie instancje powinny być tym samym obiektem");
    }
}
