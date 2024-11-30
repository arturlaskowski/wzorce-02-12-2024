package wzorce.cqrs.order.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wzorce.cqrs.order.domain.Order;
import wzorce.cqrs.order.domain.OrderAddress;
import wzorce.cqrs.order.domain.OrderItem;
import wzorce.cqrs.order.web.dto.GetOrderAddressDto;
import wzorce.cqrs.order.web.dto.GetOrderByIdQuery;
import wzorce.cqrs.order.web.dto.GetOrderItemDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderQueryMapper {

    public static GetOrderByIdQuery mapToGetOrderByIdQuery(Order order) {
        var itemsResponse = order.getItems().stream()
                .map(OrderQueryMapper::mapToItemDto)
                .toList();

        var addressDto = mapToAddressDto(order.getAddress());

        return new GetOrderByIdQuery(
                order.getId().id(),
                order.getCustomerId().id(),
                order.getPrice().amount(),
                order.getStatus(),
                itemsResponse,
                addressDto
        );
    }

    private static GetOrderItemDto mapToItemDto(OrderItem orderItem) {
        return new GetOrderItemDto(
                orderItem.getProductId(),
                orderItem.getQuantity().value(),
                orderItem.getPrice().amount(),
                orderItem.getTotalPrice().amount()
        );
    }

    private static GetOrderAddressDto mapToAddressDto(OrderAddress address) {
        return new GetOrderAddressDto(
                address.getStreet(),
                address.getPostalCode(),
                address.getCity(),
                address.getHouseNo()
        );
    }
}
