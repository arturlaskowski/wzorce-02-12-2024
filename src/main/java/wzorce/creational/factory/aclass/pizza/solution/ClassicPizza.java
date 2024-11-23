package wzorce.creational.factory.aclass.pizza.solution;

class ClassicPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje ClassicPizza");
    }
}
