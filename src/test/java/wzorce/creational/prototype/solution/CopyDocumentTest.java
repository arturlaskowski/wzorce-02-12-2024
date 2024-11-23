package wzorce.creational.prototype.solution;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class CopyDocumentTest {

    @Test
    void clonePolicyDocumentTest() {
        var securityPolicyA = new PolicyDocument("Polityka Bezpieczeństwa Informacji", "YYY.", "Zarząd", "PB/2023/01");

        var copyOfSecurityPolicyA = securityPolicyA.copy();

        // Sprawdzanie, czy obiekty nie są tymi samymi obiektami
        assertNotSame(securityPolicyA, copyOfSecurityPolicyA, "Kopia polityki powinna być innym obiektem.");

        // Sprawdzanie, czy pola są takie same
        assertThat(copyOfSecurityPolicyA).usingRecursiveComparison().isEqualTo(securityPolicyA);
    }

    @Test
    void cloneContractDocumentTest() {
        var contractA = new ContractDocument("Umowa o współpracę", "XXXX", "Zarząd", "Współpraca");

        var copyOfContractA = contractA.copy();

        // Sprawdzanie, czy obiekty nie są tymi samymi obiektami
        assertNotSame(contractA, copyOfContractA, "Kopia umowy powinna być innym obiektem.");


        // Sprawdzanie, czy pola NIE są takie same!
        assertThat(copyOfContractA).usingRecursiveComparison().isNotEqualTo(contractA);

        // Sprawdzanie, czy wszystkie pola oprócz 'createdAt' są takie same
        assertThat(copyOfContractA)
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(contractA);
    }

    @Test
    void cloneDocumentListTest() {
        var documents = List.of(
                new ContractDocument("Umowa o współpracę", "XXXX", "Zarząd", "Współpraca"),
                new PolicyDocument("Polityka Bezpieczeństwa Informacji", "YYY.", "Zarząd", "PB/2023/01")
        );

        var copyDocumentsList = documents.stream().map(Document::copy).toList();

        for (int i = 0; i < documents.size(); i++) {
            var original = documents.get(i);
            var copied = copyDocumentsList.get(i);

            assertNotSame(original, copied, "Kopia dokumentu nie powinna być tym samym obiektem co oryginał.");
        }
    }
}