package wzorce.creational.factory.aclass.pizza.problem;

class HawaiianPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje HawaiianPizza");
    }
}
