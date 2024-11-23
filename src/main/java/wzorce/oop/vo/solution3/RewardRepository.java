package wzorce.oop.vo.solution3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("rewardRepositoryVo3")
interface RewardRepository extends JpaRepository<Reward, RewardId> {
}
