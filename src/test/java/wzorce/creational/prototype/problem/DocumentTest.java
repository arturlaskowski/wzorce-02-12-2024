package wzorce.creational.prototype.problem;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentTest {

    @Test
    void createTwoTheSameDocumentTest() {
        var securityPolicyA = new PolicyDocument("Polityka Bezpieczeństwa Informacji", "YYY.", "Zarząd", "PB/2023/01");
        var securityPolicyA2 = new PolicyDocument(securityPolicyA.getTitle(), null, securityPolicyA.getAuthor(), securityPolicyA.getPolicyNumber());

        assertThat(securityPolicyA).usingRecursiveComparison().isEqualTo(securityPolicyA2);
    }
}