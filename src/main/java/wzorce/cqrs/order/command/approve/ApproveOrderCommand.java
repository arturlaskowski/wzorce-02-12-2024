package wzorce.cqrs.order.command.approve;

import jakarta.validation.constraints.NotNull;
import wzorce.cqrs.common.Command;
import wzorce.cqrs.order.domain.OrderId;

public record ApproveOrderCommand(
        @NotNull OrderId orderId) implements Command {
}
