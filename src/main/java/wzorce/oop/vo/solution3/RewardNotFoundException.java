package wzorce.oop.vo.solution3;

class RewardNotFoundException extends RuntimeException {

    public RewardNotFoundException(RewardId rewardId) {
        super("Reward not found. ID: " + rewardId);
    }
}
