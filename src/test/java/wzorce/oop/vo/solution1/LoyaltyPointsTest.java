package wzorce.oop.vo.solution1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LoyaltyPointsTest {

    @Test
    void shouldThrowExceptionWhenValueIsNegative() {
        //expected
        assertThatThrownBy(() -> new LoyaltyPoints(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Loyalty points cannot be negative");
    }

    @Test
    void shouldNotThrowExceptionWhenValueIsZeroOrPositive() {
        //expected
        assertThatNoException().isThrownBy(() -> {
            new LoyaltyPoints(0);
            new LoyaltyPoints(10);
        });
    }

    @Test
    void shouldCorrectlyAddPoints() {
        //given
        LoyaltyPoints basePoints = new LoyaltyPoints(10);
        LoyaltyPoints pointsToAdd = new LoyaltyPoints(5);
        LoyaltyPoints expected = new LoyaltyPoints(15);

        //when
        LoyaltyPoints result = basePoints.add(pointsToAdd);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldCorrectlySubtractPoints() {
        //given
        LoyaltyPoints basePoints = new LoyaltyPoints(10);
        LoyaltyPoints pointsToSubtract = new LoyaltyPoints(5);
        LoyaltyPoints expected = new LoyaltyPoints(5);

        //when
        LoyaltyPoints result = basePoints.subtract(pointsToSubtract);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenResultIsNegativeAfterSubtraction() {
        //given
        LoyaltyPoints basePoints = new LoyaltyPoints(5);
        LoyaltyPoints pointsToSubtract = new LoyaltyPoints(10);

        //expected
        assertThatThrownBy(() -> basePoints.subtract(pointsToSubtract))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Loyalty points cannot be negative");
    }
}
