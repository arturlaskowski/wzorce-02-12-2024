package wzorce.behavioral.mediator.solution;

import org.junit.jupiter.api.Test;

class AirportControlTowerTest {

    private final AirTrafficControlTower atcMediator = new AirportControlTower();
    private final Airplane boeing = new Boeing(atcMediator, "B737");
    private final Airplane airbus = new Airbus(atcMediator, "A320");
    private final Cessna cessna = new Cessna(atcMediator, "172");

    @Test
    void communicationBetweenMultipleAirplanesTest() {
        atcMediator.registerAirplane(boeing);
        atcMediator.registerAirplane(airbus);
        atcMediator.registerAirplane(cessna);

        boeing.send("Prośba o zezwolenie na lądowanie.");
        System.out.println("----------------------------");
        airbus.send("Prośba o zezwolenie na start.");
    }
}