package wzorce.behavioral.strategy.solution.java2;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinancialServicesTest {

    private static final BigDecimal LIMIT_EU_TAX = new BigDecimal(20000);

    @Test
    void taxStrategyUSTest() {
        //given
        TaxStrategy usTaxStrategy = income -> income.multiply(0.20);  // Stała stawka 20%
        FinancialServices financialServices = new FinancialServices(usTaxStrategy);
        Money income = new Money(new BigDecimal("50000"));
        Money expectedTax = new Money(new BigDecimal("10000")); // 20% of 50000

        //when
        Money calculatedTax = financialServices.calculateTax(income);

        //then
        assertEquals(expectedTax.amount(), calculatedTax.amount());
    }

    @Test
    void taxStrategyEUBelowThresholdTest() {
        //given
        TaxStrategy euTaxStrategy = income -> {
            if (income.amount().compareTo(LIMIT_EU_TAX) <= 0) {
                return income.multiply(0.10);  // Stawka 10% do 20,000
            } else {
                Money baseTax = new Money(LIMIT_EU_TAX.multiply(BigDecimal.valueOf(0.10)));
                Money additionalTax = income.subtract(new Money(LIMIT_EU_TAX)).multiply(0.25);
                return baseTax.add(additionalTax);
            }
        };
        FinancialServices financialServices = new FinancialServices(euTaxStrategy);
        Money incomeBelowThreshold = new Money(new BigDecimal("20000"));
        Money expectedTaxBelowThreshold = new Money(new BigDecimal("2000")); // 10% of 20000

        //when
        Money calculatedTaxBelowThreshold = financialServices.calculateTax(incomeBelowThreshold);

        //then
        assertEquals(expectedTaxBelowThreshold.amount(), calculatedTaxBelowThreshold.amount());
    }

    @Test
    void taxStrategyEUAboveThresholdTest() {
        //given
        TaxStrategy euTaxStrategy = new EUTaxStrategy();
        FinancialServices financialServices = new FinancialServices(euTaxStrategy);
        Money incomeAboveThreshold = new Money(new BigDecimal("50000"));
        Money baseTax = new Money(new BigDecimal("2000")); // 10% of 20000
        Money additionalTax = new Money(new BigDecimal("7500")); // 25% of the excess over 20000

        //when
        Money expectedTaxAboveThreshold = baseTax.add(additionalTax);
        Money calculatedTaxAboveThreshold = financialServices.calculateTax(incomeAboveThreshold);

        //then
        assertEquals(expectedTaxAboveThreshold.amount(), calculatedTaxAboveThreshold.amount());
    }

    @Test
    void taxStrategyAsiaTest() {
        //given
        TaxStrategy asiaTaxStrategy = income -> income.multiply(0.15);  // Stała stawka 15%
        FinancialServices financialServices = new FinancialServices(asiaTaxStrategy);
        Money income = new Money(new BigDecimal("50000"));
        Money expectedTax = new Money(new BigDecimal("7500")); // 15% of 50000

        //when
        Money calculatedTax = financialServices.calculateTax(income);

        //then
        assertEquals(expectedTax.amount(), calculatedTax.amount());
    }
}