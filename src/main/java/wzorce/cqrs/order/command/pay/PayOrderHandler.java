package wzorce.cqrs.order.command.pay;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wzorce.cqrs.common.CommandHandler;
import wzorce.cqrs.order.command.OrderNotFoundException;
import wzorce.cqrs.order.command.OrderRepository;

@Service
@RequiredArgsConstructor
public class PayOrderHandler implements CommandHandler<PayOrderCommand> {

    private final OrderRepository orderRepository;

    @Transactional
    public void handle(PayOrderCommand payOrderCommand) {
        var order = orderRepository.findById(payOrderCommand.orderId())
                .orElseThrow(() -> new OrderNotFoundException(payOrderCommand.orderId()));
        order.pay();
    }
}
