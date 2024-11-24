package wzorce.behavioral.strategy.solution.java2;

class TaxFactory {

    static TaxStrategy getTaxStrategy(TaxRegion region) {
        return switch (region) {
            case US -> new USTaxStrategy();
            case EU -> new EUTaxStrategy();
            case ASIA -> new AsiaTaxStrategy();
        };
    }
}
