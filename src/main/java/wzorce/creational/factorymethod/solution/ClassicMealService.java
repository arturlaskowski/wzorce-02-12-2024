package wzorce.creational.factorymethod.solution;

class ClassicMealService extends MealService {

    @Override
    Pizza createPizza() {
        return new ClassicPizza();
    }
}
