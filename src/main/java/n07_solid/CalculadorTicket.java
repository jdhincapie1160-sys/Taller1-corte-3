package n07_solid;

public class CalculadorTicket {
    private static final int IMPUESTO_PORCENTAJE = 8;
    private static final int DESCUENTO_MAXIMO = 50;

    public int aplicarDescuento(int subtotal, int porcentaje) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        if (porcentaje < 0 || porcentaje > DESCUENTO_MAXIMO) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 50");
        }
        return subtotal - subtotal * porcentaje / 100;
    }

    public int calcularImpuesto(int subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        return subtotal * IMPUESTO_PORCENTAJE / 100;
    }

    public int calcularTotal(int subtotal) {
        return subtotal + calcularImpuesto(subtotal);
    }
}
