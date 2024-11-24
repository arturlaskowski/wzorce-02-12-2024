package wzorce.behavioral.strategy.solution.java2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FinancialServices {

    private final TaxStrategy taxStrategy;

    public Money calculateTax(Money income) {
        return taxStrategy.calculateTax(income);
    }
}