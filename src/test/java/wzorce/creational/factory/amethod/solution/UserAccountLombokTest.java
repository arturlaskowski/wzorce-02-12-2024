package wzorce.creational.factory.amethod.solution;

import org.junit.jupiter.api.Test;

class UserAccountLombokTest {

    @Test
    void factoryStaticMethodLombok() {

        var user = UserAccountLombok.of("waldek", "waldek@kiepszki.pl");

        System.out.println("username: " + user.getUsername()
                + " email:" + user.getEmail());
    }
}