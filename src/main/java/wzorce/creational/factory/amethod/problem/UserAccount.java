package wzorce.creational.factory.amethod.problem;

import lombok.Getter;

@Getter
class UserAccount {
    private String username;
    private String email;
    private boolean isPremium;
    private boolean isBusiness;

    public UserAccount(String username, String email, boolean isPremium) {
        this.username = username;
        this.email = email;
        this.isPremium = isPremium;
    }


/*    public UserAccount1(String username, String email, boolean isBusiness) {
        this.username = username;
        this.email = email;
        this.isBusiness = isBusiness;
    }*/
}
