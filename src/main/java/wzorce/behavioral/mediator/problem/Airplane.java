package wzorce.behavioral.mediator.problem;

interface Airplane {

    void send(String message);

    void receive(String message);

    void registerAirplane(Airplane airplane);
}