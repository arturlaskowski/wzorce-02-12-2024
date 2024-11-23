package wzorce.creational.factory.aclass.pizza.problem;

class MealService {

    Pizza preparePizza(PizzaType pizzaType) {
        if (pizzaType == null) {
            throw new IllegalArgumentException("Pizza type cannot be null");
        }
        var pizza = switch (pizzaType) {
            case CLASSIC -> new ClassicPizza();
            case VEGAN -> new VeganPizza();
            case HAWAIIAN -> new HawaiianPizza();
        };

        pizza.prepare();
        return pizza;
    }
}
