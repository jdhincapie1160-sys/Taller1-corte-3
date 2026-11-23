package n02_encapsulamiento;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto {
    private final String nombre;
    private final String categoria;
    private BigDecimal precio;

    public Producto(String nombre, String categoria, BigDecimal precio) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        this.categoria = Objects.requireNonNull(categoria, "La categoría es obligatoria");
        setPrecio(precio);
    }

    public String nombre() {
        return nombre;
    }

    public String categoria() {
        return categoria;
    }

    public BigDecimal precio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{nombre='" + nombre + "', categoria='" + categoria + "', precio=" + precio + "}";
    }
}
