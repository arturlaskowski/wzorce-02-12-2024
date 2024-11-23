package wzorce.creational.builder.problem;

class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String gender;
    private final String address;
    private final String city;
    private final String state;
    private String postalCode;
    private String country;
    private String email;
    private String phoneNumber;
    private String occupation;
    private String company;
    private final double salary;
    private final String maritalStatus;
    private final int numberOfChildren;
    private final boolean hasDriverLicense;
    private final boolean ownsCar;
    private final String carModel;
    private final String carMake;
    private final int carYear;
    private final String socialSecurityNumber;
    private final String passportNumber;

    public Person(String firstName, String lastName, int age, String gender, String address, String city, String state,
                  String postalCode, String country, String email, String phoneNumber, String occupation, String company,
                  double salary, String maritalStatus, int numberOfChildren, boolean hasDriverLicense, boolean ownsCar,
                  String carModel, String carMake, int carYear, String socialSecurityNumber, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
        this.company = company;
        this.salary = salary;
        this.maritalStatus = maritalStatus;
        this.numberOfChildren = numberOfChildren;
        this.hasDriverLicense = hasDriverLicense;
        this.ownsCar = ownsCar;
        this.carModel = carModel;
        this.carMake = carMake;
        this.carYear = carYear;
        this.socialSecurityNumber = socialSecurityNumber;
        this.passportNumber = passportNumber;
    }

    public Person(String firstName, String lastName, int age, String gender, String address, String city, String state,
                  double salary, String maritalStatus, int numberOfChildren, boolean hasDriverLicense, boolean ownsCar,
                  String carModel, String carMake, int carYear, String socialSecurityNumber, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.state = state;
        this.salary = salary;
        this.maritalStatus = maritalStatus;
        this.numberOfChildren = numberOfChildren;
        this.hasDriverLicense = hasDriverLicense;
        this.ownsCar = ownsCar;
        this.carModel = carModel;
        this.carMake = carMake;
        this.carYear = carYear;
        this.socialSecurityNumber = socialSecurityNumber;
        this.passportNumber = passportNumber;
    }

    //następy i następny konstuktor
}

