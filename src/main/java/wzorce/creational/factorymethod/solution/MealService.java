package wzorce.creational.factorymethod.solution;

abstract class MealService {

    Pizza preparePizza() {
        Pizza pizza = createPizza();
        pizza.prepare();
        return pizza;
    }

    //inne metody

    abstract Pizza createPizza();
}
