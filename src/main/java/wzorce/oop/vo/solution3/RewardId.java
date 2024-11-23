package wzorce.oop.vo.solution3;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Embeddable
@EqualsAndHashCode
class RewardId {

    public static RewardId newOne() {
        return new RewardId(UUID.randomUUID());
    }

    private UUID id;

    public RewardId(UUID uuid) {
        this.id = uuid;
    }

    protected RewardId() {
    }

    public UUID id() {
        return id;
    }
}
