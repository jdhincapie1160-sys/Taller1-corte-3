package n10_serializacion;

public record ProductoArchivo(String codigo, String nombre, int precio, int stock) {
    public ProductoArchivo {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException("El precio y el stock no pueden ser negativos");
        }
    }
}
