package wzorce.creational.prototype.copy.shallow;

import lombok.Getter;

@Getter
abstract class Document {

    private String title;
    private String content;
    private String author;
    private Attachment attachment;

    public Document(String title, String content, String author, Attachment attachment) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.attachment = attachment;
    }

    protected Document(Document document) {
        this.title = document.title;
        this.content = document.content;
        this.author = document.author;
        this.attachment = document.attachment;
    }

    public abstract Document copy();
}

