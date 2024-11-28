package wzorce.behavioral.mediator.solution;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
class Cessna implements Airplane {

    private AirTrafficControlTower mediator;
    private String id;

    @Override
    public void send(String message) {
        log.info("Cessna {} wysyła wiadomość: {}", id, message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        log.info("Cessna {} otrzymał: {}", id, message);
    }
}