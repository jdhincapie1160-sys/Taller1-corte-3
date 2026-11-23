package n07_solid;

public class PagoNequi implements MetodoPago {
    private static final int MONTO_MINIMO = 1000;

    @Override
    public String procesarPago(int monto) {
        if (monto < MONTO_MINIMO) {
            throw new IllegalArgumentException("Nequi requiere un monto mínimo de $" + MONTO_MINIMO);
        }
        return "Nequi: pago aprobado por $" + monto + " (sin recargo)";
    }
}
