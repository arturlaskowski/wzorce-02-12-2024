package wzorce.behavioral.state.solution;

interface AccountState {

    void deposit(Account account, Money amount);

    void withdraw(Account account, Money amount);

    void close(Account account);

    void freeze(Account account);

    void activate(Account account);

    AccountStatus getStatus();
}