package n08_excepciones;

public class CajaRegistradora implements AutoCloseable {
    private boolean abierta;
    private int operaciones;
    private int unidades;
    private int total;

    public void abrir() {
        if (abierta) {
            throw new IllegalStateException("La caja ya está abierta");
        }
        abierta = true;
        operaciones = 0;
        unidades = 0;
        total = 0;
        IO.println("Turno abierto");
    }

    public int cobrar(String producto, int stockDisponible, int cantidad, int precioUnitario)
            throws StockInsuficienteException, CajaCerradaException {
        if (!abierta) {
            throw new CajaCerradaException("No se puede cobrar sin haber abierto el turno");
        }
        if (producto == null || producto.isBlank()) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (precioUnitario < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        }
        if (cantidad > stockDisponible) {
            throw new StockInsuficienteException(producto, stockDisponible, cantidad);
        }

        int subtotal = cantidad * precioUnitario;
        total += subtotal;
        operaciones++;
        unidades += cantidad;
        IO.println("Venta registrada: " + cantidad + " x " + producto + " -> $" + subtotal);
        return subtotal;
    }

    @Override
    public void close() {
        if (abierta) {
            IO.println("Arqueo automático: " + operaciones + " operaciones, " + unidades
                    + " unidades, total $" + total);
            abierta = false;
        }
    }
}
