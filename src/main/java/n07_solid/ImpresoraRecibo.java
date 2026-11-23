package n07_solid;

public class ImpresoraRecibo {
    public void imprimir(String descripcion, int montoOriginal, int porcentajeDescuento,
                         int subtotal, int impuesto, int total, String resultadoPago) {
        IO.println("=== Recibo: " + descripcion + " ===");
        IO.println("Monto original: $" + montoOriginal);
        IO.println("Descuento: " + porcentajeDescuento + "%");
        IO.println("Subtotal: $" + subtotal);
        IO.println("Impuesto (8%): $" + impuesto);
        IO.println("Total: $" + total);
        IO.println(resultadoPago);
        IO.println("");
    }
}
