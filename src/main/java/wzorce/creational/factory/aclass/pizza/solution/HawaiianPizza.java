package wzorce.creational.factory.aclass.pizza.solution;

class HawaiianPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje HawaiianPizza");
    }
}
