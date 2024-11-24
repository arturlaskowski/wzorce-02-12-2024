package wzorce.behavioral.strategy.solution.java3;

class FinancialServices {

    Money calculateTax(Money income, TaxRegion taxRegion) {
        var taxStrategy = TaxFactory.getTaxStrategy(taxRegion);
        return taxStrategy.calculateTax(income);
    }
}