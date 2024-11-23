package wzorce.creational.prototype.copy.shallow;

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
