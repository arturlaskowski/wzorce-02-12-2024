package wzorce.structural.adapter.solution;

class EuropeanPlugToAmericanSocketAdapter implements AmericanSocket {

    private final EuropeanPlug europeanPlug;

    public EuropeanPlugToAmericanSocketAdapter(EuropeanPlug plug) {
        this.europeanPlug = plug;
    }

    public void plugIn() {
        europeanPlug.connectPower();
        System.out.println("Adapter umożliwia podłączenie europejskiej wtyczki do amerykańskiego gniazdka.");
    }

}
