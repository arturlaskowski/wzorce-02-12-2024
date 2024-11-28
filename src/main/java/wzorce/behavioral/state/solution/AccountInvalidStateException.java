package wzorce.behavioral.state.solution;

class AccountInvalidStateException extends RuntimeException {

    public AccountInvalidStateException(String message) {
        super(message);
    }
}
