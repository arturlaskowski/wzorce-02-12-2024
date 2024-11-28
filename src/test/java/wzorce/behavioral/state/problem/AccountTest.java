package wzorce.behavioral.state.problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void depositActiveAccount() {
        account.deposit(new Money(new BigDecimal("500")));
        assertEquals(new Money(new BigDecimal("500")), account.getBalance());
    }

    @Test
    void depositFrozenAccountUnderLimit() {
        account.freeze();
        account.deposit(new Money(new BigDecimal("500")));
        assertEquals(new Money(new BigDecimal("500")), account.getBalance());
    }

    @Test
    void depositFrozenAccountOverLimit() {
        account.freeze();
        var amount = new Money(new BigDecimal("1000"));
        assertThrows(AccountInvalidStateException.class, () -> account.deposit(amount));
    }

    @Test
    void depositClosedAccount() {
        account.close();
        var amount = new Money(new BigDecimal("500"));
        assertThrows(AccountInvalidStateException.class, () -> account.deposit(amount));
    }

    @Test
    void withdrawActiveAccountSufficientFunds() {
        account.deposit(new Money(new BigDecimal("1000")));
        account.withdraw(new Money(new BigDecimal("500")));
        assertEquals(new Money(new BigDecimal("500")), account.getBalance());
    }

    @Test
    void withdrawActiveAccountInsufficientFunds() {
        account.deposit(new Money(new BigDecimal("400")));
        var amountToWithdraw = new Money(new BigDecimal("500"));
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(amountToWithdraw));
    }

    @Test
    void withdrawFromClosedAccountInsufficientFunds() {
        account.close();
        var amountToWithdraw = new Money(new BigDecimal("100"));
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(amountToWithdraw));
    }

    @Test
    void closeAccount() {
        account.close();
        assertEquals(AccountStatus.CLOSED, account.getStatus());
    }

    @Test
    void closeAlreadyClosedAccount() {
        account.close();
        assertThrows(AccountInvalidStateException.class, account::close);
    }

    @Test
    void freezeActiveAccount() {
        account.freeze();
        assertEquals(AccountStatus.FROZEN, account.getStatus());
    }

    @Test
    void freezeClosedAccount() {
        account.close();
        assertThrows(AccountInvalidStateException.class, () -> account.freeze());
    }

    @Test
    void activeClosedAccount() {
        account.close();
        assertThrows(AccountInvalidStateException.class, () -> account.close());
    }

    @Test
    void activateFrozenAccount() {
        account.freeze();
        account.activate();
        assertEquals(AccountStatus.ACTIVE, account.getStatus());
    }
}