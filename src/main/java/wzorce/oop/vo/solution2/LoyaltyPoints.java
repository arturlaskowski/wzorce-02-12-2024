package wzorce.oop.vo.solution2;

import jakarta.persistence.Embeddable;

@Embeddable
record LoyaltyPoints(Integer points) {

    public static final LoyaltyPoints ZERO = new LoyaltyPoints(0);

    public LoyaltyPoints {
        if (points < 0) {
            throw new IllegalArgumentException("Loyalty points cannot be negative");
        }
    }

    public LoyaltyPoints add(LoyaltyPoints loyaltyPointsToAdd) {
        return new LoyaltyPoints(this.points + loyaltyPointsToAdd.points());
    }

    public LoyaltyPoints subtract(LoyaltyPoints loyaltyPointsToSubtract) {
        return new LoyaltyPoints(this.points - loyaltyPointsToSubtract.points());
    }
}

