package n07_solid;

import java.util.Objects;

public class ProcesadorVentas {
    private final MetodoPago metodoPago;
    private final CalculadorTicket calculador;
    private final ImpresoraRecibo impresora;

    public ProcesadorVentas(MetodoPago metodoPago) {
        this(metodoPago, new CalculadorTicket(), new ImpresoraRecibo());
    }

    public ProcesadorVentas(MetodoPago metodoPago, CalculadorTicket calculador, ImpresoraRecibo impresora) {
        this.metodoPago = Objects.requireNonNull(metodoPago, "El método de pago es obligatorio");
        this.calculador = Objects.requireNonNull(calculador, "El calculador es obligatorio");
        this.impresora = Objects.requireNonNull(impresora, "La impresora es obligatoria");
    }

    public void procesar(String descripcion, int monto, int descuento) {
        int subtotal = calculador.aplicarDescuento(monto, descuento);
        int impuesto = calculador.calcularImpuesto(subtotal);
        int total = calculador.calcularTotal(subtotal);
        String resultadoPago = metodoPago.procesarPago(total);
        impresora.imprimir(descripcion, monto, descuento, subtotal, impuesto, total, resultadoPago);
    }
}
