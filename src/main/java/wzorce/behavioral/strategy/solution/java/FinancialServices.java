package wzorce.behavioral.strategy.solution.java;

class FinancialServices {

    private TaxStrategy taxStrategy;

    public FinancialServices(TaxStrategy strategy) {
        this.taxStrategy = strategy;
    }

    public void setTaxStrategy(TaxStrategy strategy) {
        this.taxStrategy = strategy;
    }

    public Money calculateTax(Money income) {
        return taxStrategy.calculateTax(income);
    }
}