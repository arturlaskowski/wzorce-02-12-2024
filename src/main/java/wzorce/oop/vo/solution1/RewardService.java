package wzorce.oop.vo.solution1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service("rewardServiceVo1")
@RequiredArgsConstructor
class RewardService {

    private final RewardRepository rewardRepository;

    public Reward createReward(RewardDto rewardDto) {
        validateRewardDate(rewardDto.startDate(), rewardDto.endDate());
        Reward reward = new Reward(rewardDto.name(), new LoyaltyPoints(rewardDto.cost()),
                rewardDto.startDate(), rewardDto.endDate());

        return rewardRepository.save(reward);
    }

    private static void validateRewardDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date must be from the future");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    @Transactional
    public Reward extendRewardDuration(Long rewardId, LocalDate newEndDate) {
        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new RewardNotFoundException(rewardId));

        validateRewardDate(reward.getStartDate(), newEndDate);
        //change the values

        return reward;
    }

    //other methods

}
