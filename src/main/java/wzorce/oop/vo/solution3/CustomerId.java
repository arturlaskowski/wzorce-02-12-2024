package wzorce.oop.vo.solution3;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Embeddable
@EqualsAndHashCode
class CustomerId {

    public static CustomerId newOne() {
        return new CustomerId(UUID.randomUUID());
    }

    private UUID id;

    public CustomerId(UUID uuid) {
        this.id = uuid;
    }

    protected CustomerId() {
    }

    public UUID id() {
        return id;
    }
}
