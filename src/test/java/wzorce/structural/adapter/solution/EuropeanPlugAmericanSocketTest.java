package wzorce.structural.adapter.solution;

import org.junit.jupiter.api.Test;

class EuropeanPlugAmericanSocketTest {

    @Test
    void plugInToAmericanSocket() {
        EuropeanPlug myEuropeanPlug = new EuropeanPlug();
        AmericanSocket adaptedSocket = new EuropeanPlugToAmericanSocketAdapter(myEuropeanPlug);
        adaptedSocket.plugIn();
    }
}