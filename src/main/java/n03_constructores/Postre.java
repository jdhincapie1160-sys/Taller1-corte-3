package n03_constructores;

import java.math.BigDecimal;
import java.util.Objects;

public class Postre {
    private final String nombre;
    private final BigDecimal precio;

    public Postre(String nombre, BigDecimal precio) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public Postre(String nombre) {
        this(nombre, new BigDecimal("3.50"));
    }

    public Postre(Postre original) {
        this(original.nombre, original.precio);
    }

    public String nombre() {
        return nombre;
    }

    public BigDecimal precio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Postre{nombre='" + nombre + "', precio=" + precio + "}";
    }
}
