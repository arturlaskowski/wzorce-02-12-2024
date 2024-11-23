package wzorce.oop.vo.solution1;

import java.time.LocalDate;

record RewardDto(String name, Integer cost, LocalDate startDate, LocalDate endDate) {
}