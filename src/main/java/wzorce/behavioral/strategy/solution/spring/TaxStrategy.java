package wzorce.behavioral.strategy.solution.spring;

interface TaxStrategy {

    Money calculateTax(Money income);

    TaxRegion region();
}
