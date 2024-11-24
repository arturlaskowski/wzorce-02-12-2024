package wzorce.behavioral.observer.solution.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("inventoryManagementServiceObsV1")
class InventoryManagementService {

    public void adjustInventory(String orderCode) {
        log.info("Zapasy zaktualizowane dla zamówienia o kodzie: {}", orderCode);
    }
}
