package wzorce.behavioral.strategy.solution.java3;

class USTaxStrategy implements TaxStrategy {

    @Override
    public Money calculateTax(Money income) {
        return income.multiply(0.20); // Stała stawka 20%
    }
}