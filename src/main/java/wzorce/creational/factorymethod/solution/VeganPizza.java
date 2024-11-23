package wzorce.creational.factorymethod.solution;

class VeganPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje VeganPizza");
    }

    @Override
    public String getName() {
        return "Vegan";
    }
}
