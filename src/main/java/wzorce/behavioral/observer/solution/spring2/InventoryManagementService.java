package wzorce.behavioral.observer.solution.spring2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("inventoryManagementServiceObsV2")
class InventoryManagementService {

    public void adjustInventory(String orderCode) {
        log.info("Zapasy zaktualizowane dla zamówienia o kodzie: {}", orderCode);
    }
}
