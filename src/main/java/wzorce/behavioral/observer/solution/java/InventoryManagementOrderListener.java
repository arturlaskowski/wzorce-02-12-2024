package wzorce.behavioral.observer.solution.java;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class InventoryManagementOrderListener implements OrderListener {

    private final InventoryManagementService inventoryManagementService;

    @Override
    public void update(Order order) {
        if (OrderStatus.ACCEPTED == order.getStatus()) {
            inventoryManagementService.adjustInventory(order.getCode());
        }
    }
}
