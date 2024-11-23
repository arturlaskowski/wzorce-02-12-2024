package wzorce.creational.prototype.copy.deep;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Attachment {

    private String link;

    public Attachment(String link) {
        this.link = link;
    }
}
