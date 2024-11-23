package wzorce.oop.vo.solution1;

class RewardNotFoundException extends RuntimeException {

    public RewardNotFoundException(Long rewardId) {
        super("Reward not found. ID: " + rewardId);
    }
}
