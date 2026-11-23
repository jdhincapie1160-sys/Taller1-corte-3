package n11_proyecto_final;

public record ProductoMenu(String codigo, String nombre, String categoria, int precio, int stock) {
    public ProductoMenu {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException("El precio y el stock no pueden ser negativos");
        }
    }

    public ProductoMenu conStock(int nuevoStock) {
        return new ProductoMenu(codigo, nombre, categoria, precio, nuevoStock);
    }
}
