package wzorce.cqrs.application.command.dto;

import jakarta.validation.constraints.NotNull;
import wzorce.cqrs.domain.OrderId;

public record ApproveOrderCommand(
        @NotNull OrderId orderId) {
}
