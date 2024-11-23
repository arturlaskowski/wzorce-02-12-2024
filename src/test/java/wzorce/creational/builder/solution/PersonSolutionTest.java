package wzorce.creational.builder.solution;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonSolutionTest {

    @Test
    void createPersonWithBuilderTest() {

        Person person = new Person.Builder()
                .firstName("Jane")
                .lastName("Smith")
                .age(45)
                .gender("Female")
                .address("456 Oak St")
                .city("Los Angeles")
                .state("CA")
                .postalCode("90001")
                .country("USA")
                .email("jane.smith@example.com")
                .phoneNumber("987-654-3210")
                .occupation("Manager")
                .company("RetailCorp")
                .salary(80000.0)
                .maritalStatus("Single")
                .hasDriverLicense(true)
                .ownsCar(true)
                .carModel("Accord")
                .carMake("Honda")
                .carYear(2018)
                .socialSecurityNumber("789-12-3456")
                .passportNumber("654321987")
                .build();

        System.out.println(person);
    }

    @Test
    void personEqualityTest() {
        var personViaConstructor = new PersonProblem(
                "John", "Doe", 30, "Male", null,
                null, null, null, "USA", "john.doe@example.com",
                null, null, null, 0.0, null,
                0, false, false, null, null,
                0, null, null
        );

        var personViaBuilder = new Person.Builder()
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .gender("Male")
                .country("USA")
                .email("john.doe@example.com")
                .build();

        assertThat(personViaConstructor).usingRecursiveComparison().isEqualTo(personViaBuilder);
    }
}