package wzorce.oop.vo.solution3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RewardServiceTest {

    @Autowired
    private RewardService rewardService;

    @Test
    void redeemReward() {
        var rewardId = RewardId.newOne();
        var customerId = CustomerId.newOne();


    }

}