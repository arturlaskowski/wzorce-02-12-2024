package wzorce.behavioral.strategy.solution.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FinancialServices {

    private final TaxFactory taxFactory;

    public Money calculateTax(Money income, TaxRegion taxRegion) {
        var taxStrategy = taxFactory.getTaxStrategy(taxRegion);
        return taxStrategy.calculateTax(income);
    }
}