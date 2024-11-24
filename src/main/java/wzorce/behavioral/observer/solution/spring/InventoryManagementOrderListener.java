package wzorce.behavioral.observer.solution.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("inventoryManagementOrderListenerObsV1")
class InventoryManagementOrderListener implements OrderListener {

    private final InventoryManagementService inventoryManagementService;

    @Override
    public void update(OrderChangedEvent orderChangedEvent) {
        if (OrderStatus.ACCEPTED == orderChangedEvent.status()) {
            inventoryManagementService.adjustInventory(orderChangedEvent.code());
        }
    }
}
