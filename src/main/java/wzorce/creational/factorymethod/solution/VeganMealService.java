package wzorce.creational.factorymethod.solution;

class VeganMealService extends MealService {

    @Override
    Pizza createPizza() {
        return new VeganPizza();
    }
}
