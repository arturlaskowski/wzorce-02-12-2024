package wzorce.cqrs.command.approve;

import jakarta.validation.constraints.NotNull;
import wzorce.cqrs.common.Command;
import wzorce.cqrs.domain.OrderId;

public record ApproveOrderCommand(
        @NotNull OrderId orderId) implements Command {
}
