package wzorce.cqrs.order.command.approve;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wzorce.cqrs.common.CommandHandler;
import wzorce.cqrs.order.command.OrderNotFoundException;
import wzorce.cqrs.order.command.OrderRepository;

@Service
@RequiredArgsConstructor
public class ApproveOrderHandler implements CommandHandler<ApproveOrderCommand> {

    private final OrderRepository orderRepository;

    @Transactional
    public void handle(ApproveOrderCommand approveOrderCommand) {
        var order = orderRepository.findById(approveOrderCommand.orderId())
                .orElseThrow(() -> new OrderNotFoundException(approveOrderCommand.orderId()));

        order.approve();
    }
}
