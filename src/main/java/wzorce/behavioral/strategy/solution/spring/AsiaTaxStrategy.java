package wzorce.behavioral.strategy.solution.spring;

import org.springframework.stereotype.Component;

@Component
class AsiaTaxStrategy implements TaxStrategy {

    @Override
    public Money calculateTax(Money income) {
        return income.multiply(0.15);  // Stała stawka 15%
    }

    @Override
    public TaxRegion region() {
        return TaxRegion.ASIA;
    }
}
