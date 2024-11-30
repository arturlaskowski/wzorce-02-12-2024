package wzorce.cqrs.web;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wzorce.cqrs.application.command.dto.CreateOrderAddressDto;
import wzorce.cqrs.application.command.dto.CreateOrderCommand;
import wzorce.cqrs.application.command.dto.CreateOrderItemDto;
import wzorce.cqrs.domain.CustomerId;
import wzorce.cqrs.web.dto.CreateOrderRequest;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderApiMapper {

    public static CreateOrderCommand mapToCreateOrderCommand(CreateOrderRequest createOrderRequest) {
        var itemsDto = createOrderRequest.items().stream()
                .map(OrderApiMapper::mapToItem)
                .collect(Collectors.toList());

        return new CreateOrderCommand(
                new CustomerId(createOrderRequest.customerId()),
                createOrderRequest.price(),
                itemsDto,
                mapToAddress(createOrderRequest.address())
        );
    }

    private static CreateOrderItemDto mapToItem(CreateOrderRequest.OrderItemRequest request) {
        return new CreateOrderItemDto(
                request.productId(),
                request.quantity(),
                request.price(),
                request.totalPrice()
        );
    }

    private static CreateOrderAddressDto mapToAddress(CreateOrderRequest.OrderAddressRequest request) {
        return new CreateOrderAddressDto(
                request.street(),
                request.postalCode(),
                request.city(),
                request.houseNo()
        );
    }
}