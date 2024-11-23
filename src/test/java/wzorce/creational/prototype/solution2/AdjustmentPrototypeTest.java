package wzorce.creational.prototype.solution2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class AdjustmentPrototypeTest {

    @Test
    void cloneAdjustmentPolicyDocumentTest() {
        var standardPolicy = new PolicyDocument("Polityka Bezpieczeństwa Informacji", "YYY.", "Zarząd", null);

        var standardPolicy1A = standardPolicy.copyWithNewPolicyNumber("1A");

        assertNotSame(standardPolicy, standardPolicy1A, "To inne obikety");

        assertThat(standardPolicy1A)
                .hasFieldOrPropertyWithValue("title", standardPolicy.getTitle())
                .hasFieldOrPropertyWithValue("content", standardPolicy.getContent())
                .hasFieldOrPropertyWithValue("author", standardPolicy.getAuthor())
                .hasFieldOrPropertyWithValue("policyNumber", "1A");
    }
}