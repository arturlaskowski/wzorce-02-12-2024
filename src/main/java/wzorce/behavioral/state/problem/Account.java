package wzorce.behavioral.state.problem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.math.BigDecimal;

import static wzorce.behavioral.state.problem.AccountStatus.*;

@Getter
@Entity(name = "accountV1")
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AccountStatus status;
    private Money balance;

    public Account() {
        this.status = ACTIVE;
        this.balance = Money.ZERO;
    }

    public void deposit(Money amount) {
        if (status == CLOSED) {
            throw new AccountInvalidStateException("Cannot deposit to a closed account.");
        } else if (status == FROZEN) {
            if (amount.isGreaterOrEqual(new Money(new BigDecimal("1000")))) {
                throw new AccountInvalidStateException("Cannot deposit more or equal than 1000 to a frozen account.");
            }
            balance = balance.add(amount);
        } else if (status == ACTIVE) {
            balance = balance.add(amount);
        }
    }

    public void withdraw(Money amount) {
        if (status == FROZEN) {
            throw new AccountInvalidStateException("Withdrawals are not allowed from a frozen account.");
        } else if (status == ACTIVE || status == CLOSED) {
            if (balance.isGreaterOrEqual(amount)) {
                balance = balance.subtract(amount);
            } else {
                throw new InsufficientFundsException("Insufficient funds.");
            }
        }
    }

    public void close() {
        if (status == CLOSED) {
            throw new AccountInvalidStateException("Account is already closed.");
        } else if (status == ACTIVE || status == FROZEN) {
            status = CLOSED;
        }
    }

    public void freeze() {
        if (status == FROZEN) {
            throw new AccountInvalidStateException("Account is already frozen.");
        } else if (status == CLOSED) {
            throw new AccountInvalidStateException("Cannot freeze a closed account.");
        } else if (status == ACTIVE) {
            status = FROZEN;
        }
    }

    public void activate() {
        if (status == ACTIVE) {
            throw new AccountInvalidStateException("Account is already active.");
        } else if (status == CLOSED) {
            throw new AccountInvalidStateException("Cannot activate a closed account.");
        } else if (status == FROZEN) {
            status = ACTIVE;
        }
    }
}
