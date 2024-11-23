package wzorce.oop.vo.solution3;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "rewardVo3")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Reward {

    @Id
    private RewardId id;

    private String name;

    @AttributeOverride(name = "points", column = @Column(name = "cost"))
    private LoyaltyPoints cost;

    private RewardDateRange rewardDateRange;

    public Reward(String name, LoyaltyPoints cost, RewardDateRange rewardDateRange) {
        this.id = RewardId.newOne();
        this.name = name;
        this.cost = cost;
        this.rewardDateRange = rewardDateRange;
    }

    public void extendRewardDuration(LocalDate localDate) {
        rewardDateRange.extendDate(localDate);
    }
}
