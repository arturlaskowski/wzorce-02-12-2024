package wzorce.cqrs.command.pay;

import jakarta.validation.constraints.NotNull;
import wzorce.cqrs.common.Command;
import wzorce.cqrs.domain.OrderId;

public record PayOrderCommand(
        @NotNull OrderId orderId) implements Command {
}
