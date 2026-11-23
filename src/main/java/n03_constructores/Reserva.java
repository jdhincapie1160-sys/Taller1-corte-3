package n03_constructores;

import java.time.LocalDate;
import java.util.Objects;

public class Reserva {
    private final String codigo;
    private final LocalDate fecha;
    private final int personas;

    public Reserva(String codigo, LocalDate fecha, int personas) {
        this.codigo = Objects.requireNonNull(codigo, "El código es obligatorio");
        this.fecha = Objects.requireNonNull(fecha, "La fecha es obligatoria");
        if (personas <= 0) {
            throw new IllegalArgumentException("El número de personas debe ser positivo");
        }
        this.personas = personas;
    }

    public Reserva(String codigo, int personas) {
        this(codigo, LocalDate.now(), personas);
    }

    public Reserva(Reserva original) {
        this(original.codigo, original.fecha, original.personas);
    }

    public String codigo() {
        return codigo;
    }

    public LocalDate fecha() {
        return fecha;
    }

    public int personas() {
        return personas;
    }

    @Override
    public String toString() {
        return "Reserva{codigo='" + codigo + "', fecha=" + fecha + ", personas=" + personas + "}";
    }
}
