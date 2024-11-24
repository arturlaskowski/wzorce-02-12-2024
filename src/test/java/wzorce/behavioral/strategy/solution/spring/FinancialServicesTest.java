package wzorce.behavioral.strategy.solution.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FinancialServicesTest {

    @Autowired
    private FinancialServices financialServices;

    @Test
    void taxStrategyUSTest() {
        //given
        Money income = new Money(new BigDecimal("50000"));
        Money expectedTax = new Money(new BigDecimal("10000")); // 20% of 50000

        //when
        Money calculatedTax = financialServices.calculateTax(income, TaxRegion.US);

        //then
        assertEquals(expectedTax.amount(), calculatedTax.amount());
    }

    @Test
    void taxStrategyEUBelowThresholdTest() {
        //given
        Money incomeBelowThreshold = new Money(new BigDecimal("20000"));
        Money expectedTaxBelowThreshold = new Money(new BigDecimal("2000")); // 10% of 20000

        //when
        Money calculatedTaxBelowThreshold = financialServices.calculateTax(incomeBelowThreshold, TaxRegion.EU);

        //then
        assertEquals(expectedTaxBelowThreshold.amount(), calculatedTaxBelowThreshold.amount());
    }

    @Test
    void taxStrategyEUAboveThresholdTest() {
        //given
        Money incomeAboveThreshold = new Money(new BigDecimal("50000"));
        Money baseTax = new Money(new BigDecimal("2000")); // 10% of 20000
        Money additionalTax = new Money(new BigDecimal("7500")); // 25% of the excess over 20000

        //when
        Money expectedTaxAboveThreshold = baseTax.add(additionalTax);
        Money calculatedTaxAboveThreshold = financialServices.calculateTax(incomeAboveThreshold, TaxRegion.EU);

        //then
        assertEquals(expectedTaxAboveThreshold.amount(), calculatedTaxAboveThreshold.amount());
    }

    @Test
    void taxStrategyAsiaTest() {
        //given
        Money income = new Money(new BigDecimal("50000"));
        Money expectedTax = new Money(new BigDecimal("7500")); // 15% of 50000

        //when
        Money calculatedTax = financialServices.calculateTax(income, TaxRegion.ASIA);

        //then
        assertEquals(expectedTax.amount(), calculatedTax.amount());
    }

}