package wzorce.behavioral.strategy.solution.spring;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
class TaxFactory {

    private final Map<TaxRegion, TaxStrategy> taxStrategyMap;

    public TaxFactory(List<TaxStrategy> strategies) {
        this.taxStrategyMap = strategies.stream()
                .collect(Collectors.toMap(TaxStrategy::region, strategy -> strategy));
    }

    public TaxStrategy getTaxStrategy(TaxRegion taxRegion) {
        TaxStrategy taxStrategy = taxStrategyMap.get(taxRegion);
        if (taxStrategy == null) {
            throw new IllegalArgumentException("Unsupported tax region: " + taxRegion);
        }

        return taxStrategy;
    }
}
