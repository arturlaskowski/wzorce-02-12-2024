package wzorce.creational.factorymethod.problem;

class HawaiianPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje HawaiianPizza");
    }
}
