package n05_interfaces;

import java.util.List;

class Main {
    void main() {
        OrdenMesa mesa = new OrdenMesa(3, 32000);
        List<Facturable> ordenes = List.of(mesa, new OrdenDomicilio(28000));

        for (Facturable orden : ordenes) {
            IO.println(orden);
            IO.println("  Subtotal: $" + orden.subtotal());
            IO.println("  Impuesto 8%: $" + orden.impuesto());
            IO.println("  Total: $" + orden.total());
        }

        IO.println("Descuento 15% en mesa: $" + mesa.aplicarDescuento(15));
        IO.println("Total de la mesa tras descuento: $" + mesa.total());

        try {
            mesa.aplicarDescuento(80);
        } catch (IllegalArgumentException e) {
            IO.println("Descuento rechazado: " + e.getMessage());
        }
    }
}
