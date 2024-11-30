package wzorce.cqrs.order.command.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import wzorce.cqrs.common.Command;
import wzorce.cqrs.order.domain.CustomerId;
import wzorce.cqrs.order.domain.OrderId;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderCommand(
        @NotNull OrderId orderId,
        @NotNull CustomerId customerId,
        @NotNull @Min(0) BigDecimal price,
        @Valid @NotNull List<CreateOrderItemDto> items,
        @Valid CreateOrderAddressDto address) implements Command {
}


