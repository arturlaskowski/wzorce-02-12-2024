package wzorce.oop.vo.solution1;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity(name = "customerVo1")
@Getter
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String name;

    private LoyaltyPoints points;

    protected Customer() {
    }

    public Customer(String email, String name) {
        this.email = email;
        this.name = name;
        this.points = LoyaltyPoints.ZERO;
    }

    public void addPoints(LoyaltyPoints pointsToAdd) {
        this.points = this.points.add(pointsToAdd);
    }

    public void subtractPoints(LoyaltyPoints pointsToSubtract) {
        this.points = this.points.subtract(pointsToSubtract);
    }

    public void resetPoints() {
        this.points = LoyaltyPoints.ZERO;
    }
}
