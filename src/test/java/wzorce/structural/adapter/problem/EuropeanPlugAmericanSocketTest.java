package wzorce.structural.adapter.problem;

import org.junit.jupiter.api.Test;

class EuropeanPlugAmericanSocketTest {

    @Test
    void plugInToAmericanSocket() {
        EuropeanPlug myEuropeanPlug = new EuropeanPlug();
        AmericanSocket americanSocket = null; //??
        americanSocket.plugIn();
    }

}