package wzorce.cqrs.application.command.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import wzorce.cqrs.domain.CustomerId;

import java.math.BigDecimal;
import java.util.List;


public record CreateOrderCommand(
        @NotNull CustomerId customerId,
        @NotNull @Min(0) BigDecimal price,
        @Valid @NotNull List<CreateOrderItemDto> items,
        @Valid CreateOrderAddressDto address) {
}


