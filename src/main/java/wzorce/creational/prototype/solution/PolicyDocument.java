package wzorce.creational.prototype.solution;

import lombok.Getter;

@Getter
class PolicyDocument extends Document {

    private String policyNumber;

    public PolicyDocument(String title, String content, String author, String policyNumber) {
        super(title, content, author);
        this.policyNumber = policyNumber;
    }

    private PolicyDocument(PolicyDocument policyDocument) {
        super(policyDocument);
        this.policyNumber = policyDocument.policyNumber;
    }

    @Override
    public Document copy() {
        return new PolicyDocument(this);
    }
}
