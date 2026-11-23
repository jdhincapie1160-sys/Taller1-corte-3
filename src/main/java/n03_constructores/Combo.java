package n03_constructores;

import java.math.BigDecimal;
import java.util.Objects;

public class Combo {
    private final String nombre;
    private final String bebida;
    private final Postre postre;
    private final BigDecimal precio;

    public Combo(String nombre, String bebida, Postre postre, BigDecimal precio) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        this.bebida = Objects.requireNonNull(bebida, "La bebida es obligatoria");
        this.postre = Objects.requireNonNull(postre, "El postre es obligatorio");
        if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public Combo(String nombre, Postre postre) {
        this(nombre, "Café americano", postre, postre.precio().add(new BigDecimal("2.00")));
    }

    public Combo(String nombre, String bebida, Postre postre) {
        this(nombre, bebida, postre, postre.precio().add(new BigDecimal("2.50")));
    }

    public String nombre() {
        return nombre;
    }

    public String bebida() {
        return bebida;
    }

    public Postre postre() {
        return postre;
    }

    public BigDecimal precio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Combo{nombre='" + nombre + "', bebida='" + bebida + "', postre=" + postre + ", precio=" + precio + "}";
    }
}
