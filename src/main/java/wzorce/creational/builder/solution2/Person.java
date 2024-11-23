package wzorce.creational.builder.solution2;

import lombok.Builder;

@Builder
class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String email;
    private String phoneNumber;
    private String occupation;
    private String company;
    private double salary;
    private String maritalStatus;
    private int numberOfChildren;
    private boolean hasDriverLicense;
    private boolean ownsCar;
    private String carModel;
    private String carMake;
    private int carYear;
    private String socialSecurityNumber;
    private String passportNumber;
}
