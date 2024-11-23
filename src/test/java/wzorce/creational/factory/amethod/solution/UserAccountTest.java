package wzorce.creational.factory.amethod.solution;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class UserAccountTest {

    @Test
    void factoryStaticMethodTest() {

        var businessAccount =
                UserAccount.createBusinessAccount("waldek", "waldek@kiepszki.pl");

        var standardAccount =
                UserAccount.createStandardAccount("marian", "marian@pazdzioch.pl");

        var premiumAccount =
                UserAccount.createPremiumAccount("ferdek", "ferdek@kiepszki.pl");


        System.out.println(businessAccount.getUsername() + " " + standardAccount.getUsername() + " " + premiumAccount.getUsername());
    }

    @Test
    void factoryStaticMethodJavaTest() {

        var dogs = List.of("Kokos", "Pimpek", "Azor");

        var price = BigDecimal.valueOf(10);

        var dogsStream = Stream.ofNullable(dogs);

        System.out.println(price);
        dogsStream.forEach(System.out::println);
    }
}