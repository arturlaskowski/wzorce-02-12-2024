package wzorce.creational.prototype.problem;

import lombok.Getter;

@Getter
class PolicyDocument extends Document {

    private String policyNumber;

    public PolicyDocument(String title, String content, String author, String policyNumber) {
        super(title, content, author);
        this.policyNumber = policyNumber;
    }
}
