package wzorce.behavioral.strategy.solution.spring;

import org.springframework.stereotype.Component;

@Component
class USTaxStrategy implements TaxStrategy {

    @Override
    public Money calculateTax(Money income) {
        return income.multiply(0.20); // Stała stawka 20%
    }

    @Override
    public TaxRegion region() {
        return TaxRegion.US;
    }
}
