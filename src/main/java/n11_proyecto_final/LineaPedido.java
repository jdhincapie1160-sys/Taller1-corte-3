package n11_proyecto_final;

public record LineaPedido(ProductoMenu producto, Tamano tamano, int cantidad) {
    public LineaPedido {
        if (producto == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        if (tamano == null) {
            throw new IllegalArgumentException("El tamaño es obligatorio");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
    }

    public int subtotal() {
        return tamano.calcularPrecio(producto.precio()) * cantidad;
    }
}
