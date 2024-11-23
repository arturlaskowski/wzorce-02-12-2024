package wzorce.creational.factorymethod.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MealServiceTest {

    @Test
    void prepareVeganPizza() {
        var veganMealService = new VeganMealService();
        var pizza = veganMealService.preparePizza();
        assertEquals("Vegan", pizza.getName());
    }

    @Test
    void prepareClassicPizza() {
        var classicMealService = new ClassicMealService();
        var pizza = classicMealService.preparePizza();
        assertEquals("Classic", pizza.getName());
    }
}