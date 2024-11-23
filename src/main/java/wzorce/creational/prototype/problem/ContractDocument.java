package wzorce.creational.prototype.problem;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
class ContractDocument extends Document {

    private String contractType;
    private LocalDateTime createdAt;

    public ContractDocument(String title, String content, String author, String contractType) {
        super(title, content, author);
        this.contractType = contractType;
        this.createdAt = LocalDateTime.now();
    }
}
