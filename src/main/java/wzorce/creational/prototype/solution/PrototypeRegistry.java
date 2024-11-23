package wzorce.creational.prototype.solution;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class PrototypeRegistry {

    static final String CLASSIC_CONTRACT = "Klasyczny kontrakt";
    static final String CLASSIC_POLICY = "Klasyczna polityka";

    private final Map<String, Document> cache = new HashMap<>();

    public PrototypeRegistry() {
        //Długotrwały proces tworzenia
        Document classicContract = new ContractDocument("Kontrakt klasyczny", "XXXX", "Zarząd", "Współpraca");
        Document classicPolicy = new PolicyDocument("Polityka Klasyczna", "YYY.", "Zarząd", "PB/2023/01");

        cache.put(CLASSIC_CONTRACT, classicContract);
        cache.put(CLASSIC_POLICY, classicPolicy);
    }

    public Document get(String key) {
        return cache.get(key).copy();
    }

    public void addToRegistry(String key, Document document) {
        cache.put(key, document);
    }
}
