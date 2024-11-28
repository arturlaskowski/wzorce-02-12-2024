package wzorce.behavioral.state.solution;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "accountV2")
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AccountStatus status;
    Money balance;

    @Transient
    private AccountState state;

    public Account() {
        setState(new ActiveState());
        this.balance = Money.ZERO;
    }

    public void deposit(Money amount) {
        getState().deposit(this, amount);
    }

    public void withdraw(Money amount) {
        getState().withdraw(this, amount);
    }

    public void close() {
        getState().close(this);
    }

    public void freeze() {
        getState().freeze(this);
    }

    public void activate() {
        getState().activate(this);
    }

    void setState(AccountState state) {
        this.state = state;
        this.status = state.getStatus();
    }

    private AccountState getState() {
        return switch (status) {
            case CLOSED -> new ClosedState();
            case FROZEN -> new FrozenState();
            case ACTIVE -> new ActiveState();
        };
    }
}
