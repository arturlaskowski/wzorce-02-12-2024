package wzorce.creational.prototype.solution2;

import lombok.Getter;

@Getter
class PolicyDocument {

    private String title;
    private String content;
    private String author;
    private String policyNumber;

    public PolicyDocument(String title, String content, String author, String policyNumber) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.policyNumber = policyNumber;
    }

    private PolicyDocument(PolicyDocument policyDocument, String newPolicyNumber) {
        this.title = policyDocument.title;
        this.content = policyDocument.content;
        this.author = policyDocument.author;
        this.policyNumber = newPolicyNumber;
    }

    // Metoda do kopiowania dokumentu z nowym numerem polityki
    public PolicyDocument copyWithNewPolicyNumber(String newPolicyNumber) {
        return new PolicyDocument(this, newPolicyNumber);
    }
}
