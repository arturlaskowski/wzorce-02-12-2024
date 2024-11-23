package wzorce.creational.builder.problem;

import org.junit.jupiter.api.Test;

class PersonProblemTest {

    @Test
    void createPersonProblemTest() {
        Person person = new Person("John", "Doe", 30, "Male",
                null, null, null, null, "USA",
                "john.doe@example.com", null, null, null,
                0.0, null, 0, false, false,
                null, null, 0, null, null);

        System.out.println(person);
    }
}