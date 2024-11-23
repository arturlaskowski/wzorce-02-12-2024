package wzorce.oop.vo.solution2;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RewardDateRangeTest {

    @Test
    void shouldThrowExceptionWhenStartDateIsInThePast() {
        //given
        LocalDate pastDate = LocalDate.now().minusDays(1);
        LocalDate futureDate = LocalDate.now().plusDays(1);

        //expected
        assertThatThrownBy(() -> new RewardDateRange(pastDate, futureDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Start date must be from the future");
    }

    @Test
    void shouldThrowExceptionWhenEndDateIsBeforeStartDate() {
        //given
        LocalDate futureDate = LocalDate.now().plusDays(1);
        LocalDate evenMoreFutureDate = LocalDate.now().plusDays(2);

        //expected
        assertThatThrownBy(() -> new RewardDateRange(evenMoreFutureDate, futureDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("End date must be after start date");
    }

    @Test
    void shouldReturnTrueWhenDateIsWithinRange() {
        //given
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        LocalDate testDate = LocalDate.now().plusDays(3);

        //when
        RewardDateRange range = new RewardDateRange(startDate, endDate);

        //then
        assertThat(range.isAvailableAt(testDate)).isTrue();
    }

    @Test
    void shouldReturnFalseWhenDateIsOutsideRange() {
        //given
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        LocalDate beforeStartDate = LocalDate.now();
        LocalDate afterEndDate = LocalDate.now().plusDays(6);

        //when
        RewardDateRange range = new RewardDateRange(startDate, endDate);

        //then
        assertThat(range.isAvailableAt(beforeStartDate)).isFalse();
        assertThat(range.isAvailableAt(afterEndDate)).isFalse();
    }

    @Test
    void shouldUpdateEndDateWhenNewEndDateIsAfterCurrentEndDate() {
        //given
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        LocalDate newEndDate = LocalDate.now().plusDays(10);

        //when
        RewardDateRange range = new RewardDateRange(startDate, endDate);
        RewardDateRange updatedRange = range.extendDate(newEndDate);

        //then
        assertThat(updatedRange.endDate()).isEqualTo(newEndDate);
    }

    @Test
    void shouldThrowExceptionWhenNewEndDateIsBeforeCurrentEndDate() {
        //given
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(5);
        LocalDate newEndDate = LocalDate.now().plusDays(3); // Before current end date

        //when
        RewardDateRange range = new RewardDateRange(startDate, endDate);

        //expected
        assertThatThrownBy(() -> range.extendDate(newEndDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("New end date must be after old end date");
    }
}