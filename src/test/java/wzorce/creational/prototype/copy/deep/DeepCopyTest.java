package wzorce.creational.prototype.copy.deep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;

class DeepCopyTest {

    @Test
    void cloneDocumentWithAttachment() {
        var attachment = new Attachment("LinkDoCzegoś");
        var contractA = new ContractDocument("Umowa o współpracę", "XXXX", "Zarząd", attachment, "Współpraca");

        var copyOfContractA = contractA.copy();

        assertNotSame(contractA, copyOfContractA, "Kopia umowy powinna być innym obiektem.");

        assertNotSame(attachment, copyOfContractA.getAttachment(), "Teraz to są inne obiekty :)");

        attachment.setLink("Konik");

        System.out.println(contractA.getAttachment().getLink());
        System.out.println(copyOfContractA.getAttachment().getLink());
    }
}