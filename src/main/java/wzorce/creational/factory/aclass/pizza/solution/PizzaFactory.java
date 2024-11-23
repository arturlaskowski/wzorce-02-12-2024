package wzorce.creational.factory.aclass.pizza.solution;

class PizzaFactory {

    Pizza createPizza(PizzaType pizzaType) {
        if (pizzaType == null) {
            throw new IllegalArgumentException("Pizza type cannot be null");
        }
        return switch (pizzaType) {
            case CLASSIC -> new ClassicPizza();
            case VEGAN -> new VeganPizza();
            case HAWAIIAN -> new HawaiianPizza();
        };
    }
}
