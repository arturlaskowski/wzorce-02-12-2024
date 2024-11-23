package wzorce.creational.builder.solution;

class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String gender;
    private final String address;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;
    private final String email;
    private final String phoneNumber;
    private final String occupation;
    private final String company;
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

    private Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.gender = builder.gender;
        this.address = builder.address;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.occupation = builder.occupation;
        this.company = builder.company;
        this.salary = builder.salary;
        this.maritalStatus = builder.maritalStatus;
        this.numberOfChildren = builder.numberOfChildren;
        this.hasDriverLicense = builder.hasDriverLicense;
        this.ownsCar = builder.ownsCar;
        this.carModel = builder.carModel;
        this.carMake = builder.carMake;
        this.carYear = builder.carYear;
        this.socialSecurityNumber = builder.socialSecurityNumber;
        this.passportNumber = builder.passportNumber;
    }

    public static class Builder {
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

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder occupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Builder maritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public Builder numberOfChildren(int numberOfChildren) {
            this.numberOfChildren = numberOfChildren;
            return this;
        }

        public Builder hasDriverLicense(boolean hasDriverLicense) {
            this.hasDriverLicense = hasDriverLicense;
            return this;
        }

        public Builder ownsCar(boolean ownsCar) {
            this.ownsCar = ownsCar;
            return this;
        }

        public Builder carModel(String carModel) {
            this.carModel = carModel;
            return this;
        }

        public Builder carMake(String carMake) {
            this.carMake = carMake;
            return this;
        }

        public Builder carYear(int carYear) {
            this.carYear = carYear;
            return this;
        }

        public Builder socialSecurityNumber(String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public Builder passportNumber(String passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
