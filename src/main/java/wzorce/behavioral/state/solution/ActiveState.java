package wzorce.behavioral.state.solution;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ActiveState implements AccountState {

    @Override
    public void deposit(Account account, Money amount) {
        account.balance = account.balance.add(amount);
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
        account.setState(new ClosedState());
    }

    @Override
    public void freeze(Account account) {
        account.setState(new FrozenState());
    }

    @Override
    public void activate(Account account) {
        throw new AccountInvalidStateException("Account is already active.");
    }

    @Override
    public AccountStatus getStatus() {
        return AccountStatus.ACTIVE;
    }
}