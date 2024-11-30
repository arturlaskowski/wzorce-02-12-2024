package wzorce.cqrs.command.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wzorce.cqrs.common.CommandHandler;
import wzorce.cqrs.command.OrderRepository;
import wzorce.cqrs.domain.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateOrderHandler implements CommandHandler<CreateOrderCommand> {

    private final OrderRepository orderRepository;

    public void handle(CreateOrderCommand createOrderCommand) {
        var items = convertToCreateOrderItems(createOrderCommand.items());
        var orderAddress = convertToCreateOrderAddress(createOrderCommand.address());

        var order = new Order(createOrderCommand.orderId(), createOrderCommand.customerId(), new Money(createOrderCommand.price()),
                items, orderAddress);

        orderRepository.save(order);
    }

    private List<OrderItem> convertToCreateOrderItems(List<CreateOrderItemDto> itemDtos) {
        return itemDtos.stream()
                .map(itemDto -> new OrderItem(
                        itemDto.productId(),
                        new Money(itemDto.price()),
                        new Quantity(itemDto.quantity()),
                        new Money(itemDto.totalPrice())
                )).toList();
    }

    public OrderAddress convertToCreateOrderAddress(CreateOrderAddressDto addressDto) {
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
