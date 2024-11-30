package wzorce.cqrs.order.command.pay;

import jakarta.validation.constraints.NotNull;
import wzorce.cqrs.common.Command;
import wzorce.cqrs.order.domain.OrderId;

public record PayOrderCommand(
        @NotNull OrderId orderId) implements Command {
}
