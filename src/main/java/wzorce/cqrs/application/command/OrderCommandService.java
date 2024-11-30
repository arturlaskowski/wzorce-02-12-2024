package wzorce.cqrs.application.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wzorce.cqrs.application.command.dto.ApproveOrderCommand;
import wzorce.cqrs.application.command.dto.CreateOrderCommand;
import wzorce.cqrs.application.command.dto.PayOrderCommand;
import wzorce.cqrs.application.exeption.OrderNotFoundException;
import wzorce.cqrs.domain.Money;
import wzorce.cqrs.domain.Order;
import wzorce.cqrs.domain.OrderId;

@Service
@RequiredArgsConstructor
public class OrderCommandService {

    private final OrderRepository orderRepository;

    public OrderId createOrder(CreateOrderCommand createOrderCommand) {
        var items = OrderCommandMapper.convertToCreateOrderItems(createOrderCommand.items());
        var orderAddress = OrderCommandMapper.convertToCreateOrderAddress(createOrderCommand.address());

        var order = new Order(createOrderCommand.customerId(), new Money(createOrderCommand.price()),
                items, orderAddress);

        return orderRepository.save(order).getId();
    }

    @Transactional
    public void pay(PayOrderCommand payOrderCommand) {
        var order = orderRepository.findById(payOrderCommand.orderId()).orElseThrow(() -> new OrderNotFoundException(payOrderCommand.orderId()));
        order.pay();
    }

    @Transactional
    public void approve(ApproveOrderCommand approveOrderCommand) {
        var order = orderRepository.findById(approveOrderCommand.orderId())
                .orElseThrow(() -> new OrderNotFoundException(approveOrderCommand.orderId()));

        order.approve();
    }
}
