package wzorce.behavioral.state.solution;

import java.math.BigDecimal;

class FrozenState implements AccountState {

    @Override
    public void deposit(Account account, Money amount) {
        if (amount.isGreaterOrEqual(new Money(new BigDecimal("1000")))) {
            throw new AccountInvalidStateException("Cannot deposit more or equal than 1000 to a frozen account.");
        }
        account.balance = account.balance.add(amount);
    }

    @Override
    public void withdraw(Account account, Money amount) {
        throw new AccountInvalidStateException("Withdrawals are not allowed from a frozen account.");
    }

    @Override
    public void close(Account account) {
        account.setState(new ClosedState());
    }

    @Override
    public void freeze(Account account) {
        throw new AccountInvalidStateException("Account is already frozen.");
    }

    @Override
    public void activate(Account account) {
        account.setState(new ActiveState());
    }

    @Override
    public AccountStatus getStatus() {
        return AccountStatus.FROZEN;
    }
}
