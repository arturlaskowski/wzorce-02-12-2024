package wzorce.creational.factory.aclass.pizza.solution;

class MealService {

    Pizza preparePizza(PizzaType pizzaType) {
        var pizzaFactory = new PizzaFactory();
        var pizza = pizzaFactory.createPizza(pizzaType);
        pizza.prepare();
        return pizza;
    }
}
