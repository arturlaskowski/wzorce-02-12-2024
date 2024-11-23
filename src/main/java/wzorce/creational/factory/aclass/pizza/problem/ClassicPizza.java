package wzorce.creational.factory.aclass.pizza.problem;

class ClassicPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Przygotowuje ClassicPizza");
    }
}
