package wzorce.creational.singleton.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class SingletonSolutionTest {

    @Test
    void shouldReturnTheSameInstance() {
        DatabaseConnectionManager firstInstance = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager secondInstance = DatabaseConnectionManager.getInstance();

        assertSame(firstInstance, secondInstance, "Obie instancje powinny być tym samym obiektem");
    }
}
