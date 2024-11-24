package wzorce.behavioral.strategy.solution.spring;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class EUTaxStrategy implements TaxStrategy {

    private static final BigDecimal LIMIT = new BigDecimal(20000);

    @Override
    public Money calculateTax(Money income) {
        if (income.amount().compareTo(LIMIT) <= 0) {
            return income.multiply(0.10);  // Stawka 10% do 20,000
        } else {
            Money baseTax = new Money(LIMIT.multiply(BigDecimal.valueOf(0.10)));
            Money additionalTax = income.subtract(new Money(LIMIT)).multiply(0.25);
            return baseTax.add(additionalTax);
        }
    }

    @Override
    public TaxRegion region() {
        return TaxRegion.EU;
    }
}
