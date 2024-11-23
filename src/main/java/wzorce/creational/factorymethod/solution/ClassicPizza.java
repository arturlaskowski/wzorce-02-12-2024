package wzorce.creational.factorymethod.solution;

class ClassicPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje ClassicPizza");
    }

    @Override
    public String getName() {
        return "Classic";
    }
}
