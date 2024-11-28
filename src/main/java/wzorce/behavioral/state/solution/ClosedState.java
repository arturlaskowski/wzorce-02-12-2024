package wzorce.behavioral.state.solution;

class ClosedState implements AccountState {

    @Override
    public void deposit(Account account, Money amount) {
        throw new AccountInvalidStateException("Cannot deposit to a closed account.");
    }

    @Override
    public void withdraw(Account account, Money amount) {
        if (account.balance.isGreaterOrEqual(amount)) {
            account.balance = account.balance.subtract(amount);
        } else {
            throw new InsufficientFundsException("Insufficient funds.");
        }
    }

    @Override
    public void close(Account account) {
        throw new AccountInvalidStateException("Account is already closed.");
    }

    @Override
    public void freeze(Account account) {
        throw new AccountInvalidStateException("Account is already frozen");
    }

    @Override
    public void activate(Account account) {
        throw new AccountInvalidStateException("Cannot activate a closed account.");
    }

    @Override
    public AccountStatus getStatus() {
        return AccountStatus.CLOSED;
    }
}