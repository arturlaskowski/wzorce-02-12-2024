package wzorce.behavioral.mediator.problem;

import org.junit.jupiter.api.Test;

class AirplaneTest {

    private final Airplane boeing = new Boeing("B737");
    private final Airplane airbus = new Airbus("A320");
    private final Cessna cessna = new Cessna("172");

    @Test
    void communicationBetweenMultipleAirplanesTest() {
        boeing.registerAirplane(airbus);
        boeing.registerAirplane(cessna);

        airbus.registerAirplane(boeing);
        airbus.registerAirplane(cessna);

        cessna.registerAirplane(boeing);
        cessna.registerAirplane(airbus);

        boeing.send("Prośba o zezwolenie na lądowanie.");
        System.out.println("----------------------------");
        airbus.send("Prośba o zezwolenie na start.");
    }
}