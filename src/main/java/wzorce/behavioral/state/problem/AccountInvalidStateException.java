package wzorce.behavioral.state.problem;

class AccountInvalidStateException extends RuntimeException {

    public AccountInvalidStateException(String message) {
        super(message);
    }
}
