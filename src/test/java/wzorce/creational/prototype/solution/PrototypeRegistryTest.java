package wzorce.creational.prototype.solution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@SpringBootTest
class PrototypeRegistryTest {

    @Autowired
    private PrototypeRegistry prototypeRegistry;

    @Test
    void cloneContractFromPrototypeRegistryTest() {
        var copyOfClassicContract = prototypeRegistry.get(PrototypeRegistry.CLASSIC_CONTRACT);
        var copyOfClassicContract2 = prototypeRegistry.get(PrototypeRegistry.CLASSIC_CONTRACT);

        // Sprawdzanie, czy kopie nie są tymi samymi obiektami
        assertNotSame(copyOfClassicContract, copyOfClassicContract2, "Kopia nowo dodanego kontraktu nie powinna być tym samym obiektem.");
    }

    @Test
    void addNewContractToPrototypeRegistryTest() {
        var newOftenUsedContract = new ContractDocument("Umowa o współpracę", "XXXX", "Zarząd", "Współpraca");
        prototypeRegistry.addToRegistry("Umowa najmu", newOftenUsedContract);

        var copyOfNewOftenUsedContract = prototypeRegistry.get("Umowa najmu");

        // Sprawdzanie, czy kopie nie są tymi samymi obiektami
        assertNotSame(newOftenUsedContract, copyOfNewOftenUsedContract, "Kopia nowo dodanego kontraktu nie powinna być tym samym obiektem.");

        // Sprawdzanie, czy wszystkie pola oprócz 'createdAt' są takie same
        assertThat(copyOfNewOftenUsedContract)
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(newOftenUsedContract);
    }
}
