package wzorce.creational.prototype.solution;

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

    protected Document(Document document) {
        this.title = document.title;
        this.content = document.content;
        this.author = document.author;
    }

    public abstract Document copy();

    public void signDocument() {
        log.info("Dokument podpisany");
    }

    //inne metody

}

