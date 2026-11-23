package n07_solid;

public class PagoTarjeta implements MetodoPago {
    @Override
    public String procesarPago(int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        int total = monto * 102 / 100;
        return "Tarjeta: pago aprobado por $" + total + " (incluye 2% de recargo bancario)";
    }
}
