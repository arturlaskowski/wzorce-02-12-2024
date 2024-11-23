package wzorce.oop.vo.solution2;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "rewardVo2")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @AttributeOverride(name = "points", column = @Column(name = "cost"))
    private LoyaltyPoints cost;

    private RewardDateRange rewardDateRange;

    public Reward(String name, LoyaltyPoints cost, RewardDateRange rewardDateRange) {
        this.name = name;
        this.cost = cost;
        this.rewardDateRange = rewardDateRange;
    }

    public void extendRewardDuration(LocalDate localDate) {
        rewardDateRange.extendDate(localDate);
    }
}
