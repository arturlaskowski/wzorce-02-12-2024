package wzorce.cqrs.web;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wzorce.cqrs.command.create.CreateOrderAddressDto;
import wzorce.cqrs.command.create.CreateOrderCommand;
import wzorce.cqrs.command.create.CreateOrderItemDto;
import wzorce.cqrs.domain.CustomerId;
import wzorce.cqrs.domain.OrderId;
import wzorce.cqrs.web.dto.CreateOrderRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderApiMapper {

    public static CreateOrderCommand mapToCreateOrderCommand(OrderId orderId, CreateOrderRequest createOrderRequest) {
        var itemsDto = createOrderRequest.items().stream()
                .map(OrderApiMapper::mapToItem)
                .toList();

        return new CreateOrderCommand(
                orderId,
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