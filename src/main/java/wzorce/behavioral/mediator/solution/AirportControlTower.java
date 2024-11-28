package wzorce.behavioral.mediator.solution;

import java.util.ArrayList;
import java.util.List;

// Mediator
class AirportControlTower implements AirTrafficControlTower {

    private final List<Airplane> airplanes = new ArrayList<>();

    public void registerAirplane(Airplane airplane) {
        airplanes.add(airplane);
    }

    public void sendMessage(String message, Airplane originator) {
        for (Airplane airplane : airplanes) {
            if (airplane != originator) { // uniknięcie wysyłania wiadomości do siebie
                airplane.receive(message);
            }
        }
    }
}