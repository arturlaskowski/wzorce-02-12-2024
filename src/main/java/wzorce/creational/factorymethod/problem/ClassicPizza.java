package wzorce.creational.factorymethod.problem;

class ClassicPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje ClassicPizza");
    }
}
