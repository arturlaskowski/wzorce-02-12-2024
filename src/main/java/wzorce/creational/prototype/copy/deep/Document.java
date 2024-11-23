package wzorce.creational.prototype.copy.deep;

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
        this.attachment = new Attachment(document.attachment.getLink());  // lub Attachment może mieć metodę copy
    }

    public abstract Document copy();

}

