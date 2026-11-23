package n11_proyecto_final;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {
    void main() {
        InventarioService inventario = new InventarioService();
        PagoService pagos = new PagoService();
        List<Pedido> pedidos = new ArrayList<>();

        try {
            inventario.cargar();
            IO.println("Menú cargado: " + inventario.productos().size() + " productos");

            registrar(pedidos, 1, inventario, pagos, "CAP-01", Tamano.MEDIANO, 2, MetodoPago.EFECTIVO);
            registrar(pedidos, 2, inventario, pagos, "LIM-01", Tamano.GRANDE, 3, MetodoPago.TARJETA);
            registrar(pedidos, 3, inventario, pagos, "BRO-01", Tamano.PEQUENO, 1, MetodoPago.NEQUI);

            try {
                inventario.reservar("CAP-01", 999);
            } catch (PedidoInvalidoException e) {
                IO.println("Compra rechazada: " + e.getMessage());
            }

            ReporteService reporte = new ReporteService(pedidos);
            IO.println("Ventas del día por categoría:");
            reporte.ventasPorCategoria().forEach((categoria, total) ->
                    IO.println("  " + categoria + ": $" + total));
            IO.println("Total general: $" + reporte.totalGeneral());
            IO.println("Pedidos por método: " + reporte.pedidosPorMetodo());

            inventario.guardar();
            IO.println("Inventario actualizado guardado en " + inventario.ruta());
        } catch (IOException e) {
            IO.println("Error de archivo: " + e.getMessage());
        } catch (PedidoInvalidoException e) {
            IO.println("Error de pedido: " + e.getMessage());
        }
    }

    private static void registrar(List<Pedido> pedidos, int numero, InventarioService inventario,
                                  PagoService pagos, String codigo, Tamano tamano, int cantidad,
                                  MetodoPago metodo) throws PedidoInvalidoException {
        ProductoMenu producto = inventario.buscar(codigo)
                .orElseThrow(() -> new PedidoInvalidoException("Producto no encontrado: " + codigo));
        inventario.reservar(codigo, cantidad);

        Pedido pedido = new Pedido(numero, metodo);
        pedido.agregar(new LineaPedido(producto, tamano, cantidad));
        PagoService.ResultadoPago resultado = pagos.pagar(metodo, pedido.subtotal());
        pedidos.add(pedido);

        IO.println("Pedido #" + numero + ": " + cantidad + " x " + producto.nombre() + " " + tamano
                + " -> $" + pedido.subtotal() + " | " + resultado.mensaje());
    }
}
