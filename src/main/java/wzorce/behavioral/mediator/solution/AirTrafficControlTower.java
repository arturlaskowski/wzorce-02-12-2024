package wzorce.behavioral.mediator.solution;

// Mediator Interface
interface AirTrafficControlTower {

    void sendMessage(String message, Airplane airplane);
    void registerAirplane(Airplane airplane);
}