package wzorce.oop.vo.problem;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "rewardVo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer cost;

    private LocalDate startDate;

    private LocalDate endDate;

    public Reward(String name, Integer cost, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
        validatePoints();
    }

    private void validatePoints() {
        if (cost < 0) {
            throw new IllegalArgumentException("Loyalty points cannot be negative");
        }
    }
}

