package wzorce.creational.prototype.copy.shallow;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
class ContractDocument extends Document {

    private LocalDateTime createdAt;
    private String contractType;

    public ContractDocument(String title, String content, String author, Attachment attachment, String contractType) {
        super(title, content, author, attachment);
        this.createdAt = LocalDateTime.now();
        this.contractType = contractType;
    }

    private ContractDocument(ContractDocument contractDocument) {
        super(contractDocument);
        this.contractType = contractDocument.contractType;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public Document copy() {
        return new ContractDocument(this);
    }
}