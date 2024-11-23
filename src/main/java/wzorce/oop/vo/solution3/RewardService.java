package wzorce.oop.vo.solution3;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service("rewardServiceVo3")
@RequiredArgsConstructor
class RewardService {

    private final RewardRepository rewardRepository;

    public Reward createReward(RewardDto rewardDto) {
        Reward reward = new Reward(rewardDto.name(), new LoyaltyPoints(rewardDto.cost()),
                new RewardDateRange(rewardDto.startDate(), rewardDto.endDate()));

        return rewardRepository.save(reward);
    }

    @Transactional
    public void extendRewardDuration(RewardId rewardId, LocalDate newEndDate) {
        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new RewardNotFoundException(rewardId));

        reward.extendRewardDuration(newEndDate);
    }


    public void redeemReward(CustomerId customerId, RewardId rewardId) {
        //LOGIC
    }
}
