package wzorce.creational.builder.solution2;

import org.junit.jupiter.api.Test;

class PersonSolution2Test {

    @Test
    void createPersonWithBuilderTest() {
        Person person = Person.builder()
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

}