package wzorce.creational.factory.aclass.pizza.problem;

class VeganPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje VeganPizza");
    }
}
