package n05_interfaces;

public class OrdenDomicilio implements Facturable {
    private static final int COSTO_ENVIO = 4000;

    private final int montoBase;

    public OrdenDomicilio(int montoBase) {
        if (montoBase < 0) {
            throw new IllegalArgumentException("El monto base no puede ser negativo");
        }
        this.montoBase = montoBase;
    }

    public int montoBase() {
        return montoBase;
    }

    public int costoEnvio() {
        return COSTO_ENVIO;
    }

    @Override
    public int subtotal() {
        return montoBase + COSTO_ENVIO;
    }

    @Override
    public String toString() {
        return "OrdenDomicilio{montoBase=" + montoBase + ", envío=" + COSTO_ENVIO + "}";
    }
}
