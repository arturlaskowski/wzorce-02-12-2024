package wzorce.oop.vo.solution1;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "rewardVo1")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @AttributeOverride(name = "points", column = @Column(name = "cost"))
    private LoyaltyPoints cost;

    private LocalDate startDate;

    private LocalDate endDate;

    public Reward(String name, LoyaltyPoints cost, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
