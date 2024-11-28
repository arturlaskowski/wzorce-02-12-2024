package wzorce.behavioral.mediator.problem;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class Boeing implements Airplane {

    private final List<Airplane> otherAirplanes = new ArrayList<>();
    private final String id;

    public Boeing(String id) {
        this.id = id;
    }

    public void registerAirplane(Airplane airplane) {
        otherAirplanes.add(airplane);
    }

    @Override
    public void send(String message) {
        log.info("Boeing {} wysyła wiadomość: {}", id, message);
        otherAirplanes.forEach(airplane -> airplane.receive(message));
    }

    @Override
    public void receive(String message) {
        log.info("Boeing {} otrzymał: {}", id, message);
    }
}
