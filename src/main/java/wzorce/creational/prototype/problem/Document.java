package wzorce.creational.prototype.problem;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class Document {

    @Getter
    private String title;

    private String content;

    @Getter
    private String author;

    public Document(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void signDocument() {
        log.info("Dokument podpisany");
    }

    //inne metody
}

