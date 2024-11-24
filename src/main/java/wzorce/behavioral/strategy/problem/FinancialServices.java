package wzorce.behavioral.strategy.problem;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
class FinancialServices {

    private static final BigDecimal LIMIT_EU_TAX = new BigDecimal(20000);

    public Money calculateTax(Money income, TaxRegion taxRegion) {
        if (TaxRegion.US == taxRegion) {
            return income.multiply(0.20);  // Stała stawka 20%
        } else if (TaxRegion.EU == taxRegion) {
            if (income.amount().compareTo(LIMIT_EU_TAX) <= 0) {
                return income.multiply(0.10);  // Stawka 10% do 20,000
            } else {
                Money baseTax = new Money(LIMIT_EU_TAX.multiply(BigDecimal.valueOf(0.10)));
                Money additionalTax = income.subtract(new Money(LIMIT_EU_TAX)).multiply(0.25);
                return baseTax.add(additionalTax);
            }
        } else if (TaxRegion.ASIA == taxRegion) {
            return income.multiply(0.15);  // Stała stawka 15%
        } else {
            throw new IllegalArgumentException("Unsupported region");
        }
    }
}
