package n07_solid;

public class PagoEfectivo implements MetodoPago {
    @Override
    public String procesarPago(int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        return "Efectivo: pago aprobado por $" + monto;
    }
}
