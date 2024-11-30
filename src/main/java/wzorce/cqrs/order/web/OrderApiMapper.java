package wzorce.cqrs.order.web;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wzorce.cqrs.order.command.create.CreateOrderAddressDto;
import wzorce.cqrs.order.command.create.CreateOrderCommand;
import wzorce.cqrs.order.command.create.CreateOrderItemDto;
import wzorce.cqrs.order.domain.CustomerId;
import wzorce.cqrs.order.domain.OrderId;
import wzorce.cqrs.order.web.dto.CreateOrderRequest;

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