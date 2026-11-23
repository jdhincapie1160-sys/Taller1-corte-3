package n09_colecciones;

public record ItemVenta(String categoria, String producto, int cantidad, int precioUnitario) {
    public ItemVenta {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoría es obligatoria");
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
    }

    public int subtotal() {
        return cantidad * precioUnitario;
    }
}
