package wzorce.oop.vo.problem;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity(name = "customerVo")
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

    private Integer points;

    protected Customer() {
    }

    public Customer(String email, String name) {
        this.email = email;
        this.name = name;
        this.points = 0;
    }

    public void addPoints(Integer pointsToAdd) {
        this.points = this.points + pointsToAdd;
    }

    public void subtractPoints(Integer pointsToSubtract) {
        int pointsAfterSubtract = this.points - pointsToSubtract;
        if (pointsAfterSubtract < 0) {
            throw new IllegalArgumentException("Loyalty points cannot be negative");
        }
        this.points = pointsAfterSubtract;
    }

    public void resetPoints() {
        this.points = 0;
    }
}
