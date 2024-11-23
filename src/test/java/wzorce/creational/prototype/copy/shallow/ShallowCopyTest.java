package wzorce.creational.prototype.copy.shallow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class ShallowCopyTest {

    @Test
    void cloneDocumentWithAttachment() {
        var attachment = new Attachment("LinkDoCzegoś");
        var contractA = new ContractDocument("Umowa o współpracę", "XXXX", "Zarząd", attachment, "Współpraca");

        var copyOfContractA = contractA.copy();

        assertNotSame(contractA, copyOfContractA, "Kopia umowy powinna być innym obiektem.");

        assertSame(attachment, copyOfContractA.getAttachment(), "To ten sam obiekty!.");

        attachment.setLink("Konik");

        System.out.println(contractA.getAttachment().getLink());
        System.out.println(copyOfContractA.getAttachment().getLink());
    }
}