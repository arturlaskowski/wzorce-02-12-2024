package wzorce.behavioral.strategy.solution.java;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinancialServicesTest {

    TaxStrategy strategy = new USTaxStrategy();
    FinancialServices financialServices = new FinancialServices(strategy);

    @Test
    void taxStrategyUSTest() {
        //given
        financialServices.setTaxStrategy(new USTaxStrategy());

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
        financialServices.setTaxStrategy(new EUTaxStrategy());

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
        financialServices.setTaxStrategy(new EUTaxStrategy());

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
        financialServices.setTaxStrategy(new AsiaTaxStrategy());

        Money income = new Money(new BigDecimal("50000"));
        Money expectedTax = new Money(new BigDecimal("7500")); // 15% of 50000

        //when
        Money calculatedTax = financialServices.calculateTax(income);

        //then
        assertEquals(expectedTax.amount(), calculatedTax.amount());
    }
}