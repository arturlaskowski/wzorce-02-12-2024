package wzorce.oop.vo.solution2;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
record RewardDateRange(LocalDate startDate, LocalDate endDate) {

    public RewardDateRange {
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date must be from the future");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }

    public boolean isAvailableAt(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public RewardDateRange extendDate(LocalDate newEndDate) {
        if (newEndDate.isBefore(endDate)) {
            throw new IllegalArgumentException("New end date must be after old end date");
        }
        return new RewardDateRange(startDate, newEndDate);
    }
}