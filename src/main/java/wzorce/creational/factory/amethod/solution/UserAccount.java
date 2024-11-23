package wzorce.creational.factory.amethod.solution;

import lombok.Getter;

@Getter
class UserAccount {
    private String username;
    private String email;
    private boolean isPremium;
    private boolean isBusiness;

    private UserAccount(String username, String email, boolean isPremium, boolean isBusiness) {
        this.username = username;
        this.email = email;
        this.isPremium = isPremium;
        this.isBusiness = isBusiness;
    }

    public static UserAccount createStandardAccount(String username, String email) {
        return new UserAccount(username, email, false, false);
    }

    public static UserAccount createPremiumAccount(String username, String email) {
        return new UserAccount(username, email, true, false);
    }

    public static UserAccount createBusinessAccount(String username, String email) {
        return new UserAccount(username, email, true, true);
    }


}
