package wzorce.behavioral.strategy.solution.java2;

class FinancialServices {

    Money calculateTax(Money income, TaxRegion taxRegion) {
        var taxStrategy = TaxFactory.getTaxStrategy(taxRegion);
        return taxStrategy.calculateTax(income);
    }
}