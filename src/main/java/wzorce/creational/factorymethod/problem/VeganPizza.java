package wzorce.creational.factorymethod.problem;

class VeganPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje VeganPizza");
    }
}
