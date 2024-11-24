package wzorce.behavioral.observer.solution.spring2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("inventoryManagementOrderListenerObsV2")
class InventoryManagementOrderListener {

    private final InventoryManagementService inventoryManagementService;

    @EventListener
    public void handleEvent(OrderChangedEvent orderChangedEvent) {
        if (OrderStatus.ACCEPTED == orderChangedEvent.status()) {
            inventoryManagementService.adjustInventory(orderChangedEvent.code());
        }
    }
}
