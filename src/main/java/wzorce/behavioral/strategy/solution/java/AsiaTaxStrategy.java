package wzorce.behavioral.strategy.solution.java;

class AsiaTaxStrategy implements TaxStrategy {

    @Override
    public Money calculateTax(Money income) {
        return income.multiply(0.15);  // Stała stawka 15%
    }
}