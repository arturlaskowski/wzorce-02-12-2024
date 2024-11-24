package wzorce.behavioral.observer.solution.spring;

interface OrderListener {

    void update(OrderChangedEvent orderChangedEvent);
}
