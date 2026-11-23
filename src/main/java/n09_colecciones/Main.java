package n09_colecciones;

import java.util.List;

class Main {
    void main() {
        List<ItemVenta> ventas = List.of(
                new ItemVenta("Bebidas Calientes", "Café americano", 4, 4500),
                new ItemVenta("Bebidas Calientes", "Café latte", 2, 5500),
                new ItemVenta("Bebidas Calientes", "Capuchino", 3, 5000),
                new ItemVenta("Bebidas Frías", "Limonada", 6, 5500),
                new ItemVenta("Bebidas Frías", "Té helado", 2, 6000),
                new ItemVenta("Bebidas Frías", "Limonada grande", 1, 7500),
                new ItemVenta("Repostería", "Croissant", 5, 3200),
                new ItemVenta("Repostería", "Brownie", 3, 3500),
                new ItemVenta("Repostería", "Tiramisú", 2, 5000)
        );

        AnalizadorVentas analizador = new AnalizadorVentas(ventas);

        IO.println("Ventas con subtotal mayor a $10000:");
        analizador.filtrarPorMontoMinimo(10000)
                .forEach(venta -> IO.println("  " + venta.producto() + ": $" + venta.subtotal()));

        IO.println("Categorías únicas: " + analizador.categoriasUnicas());

        IO.println("Total recaudado por categoría:");
        analizador.totalPorCategoria().forEach((categoria, total) ->
                IO.println("  " + categoria + ": $" + total));

        analizador.productoMasVendido().ifPresent(venta ->
                IO.println("Producto más vendido: " + venta.producto() + " (" + venta.cantidad() + " unidades)"));
        analizador.productoMayorIngreso().ifPresent(venta ->
                IO.println("Producto de mayor ingreso: " + venta.producto() + " ($" + venta.subtotal() + ")"));
    }
}
