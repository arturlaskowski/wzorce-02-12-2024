package wzorce.cqrs.application.command;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wzorce.cqrs.application.command.dto.CreateOrderAddressDto;
import wzorce.cqrs.application.command.dto.CreateOrderItemDto;
import wzorce.cqrs.domain.Money;
import wzorce.cqrs.domain.OrderAddress;
import wzorce.cqrs.domain.OrderItem;
import wzorce.cqrs.domain.Quantity;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderCommandMapper {

    public static List<OrderItem> convertToCreateOrderItems(List<CreateOrderItemDto> itemDtos) {
        return itemDtos.stream()
                .map(itemDto -> new OrderItem(
                        itemDto.productId(),
                        new Money(itemDto.price()),
                        new Quantity(itemDto.quantity()),
                        new Money(itemDto.totalPrice())
                )).toList();
    }

    public static OrderAddress convertToCreateOrderAddress(CreateOrderAddressDto addressDto) {
        if (addressDto != null) {
            return new OrderAddress(
                    addressDto.street(),
                    addressDto.postalCode(),
                    addressDto.city(),
                    addressDto.houseNo()
            );
        }
        return null;
    }
}
