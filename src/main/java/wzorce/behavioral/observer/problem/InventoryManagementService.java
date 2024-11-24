package wzorce.behavioral.observer.problem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class InventoryManagementService {

    public void adjustInventory(String orderCode) {
        log.info("Zapasy zaktualizowane dla zamówienia o kodzie: {}", orderCode);
    }
}
