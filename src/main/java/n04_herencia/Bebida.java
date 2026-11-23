package n04_herencia;

public class Bebida {
    protected final String nombre;
    protected final int precioBase;

    public Bebida(String nombre, int precioBase) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la bebida no puede estar vacío");
        }
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo");
        }
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public String nombre() {
        return nombre;
    }

    public int precioBase() {
        return precioBase;
    }

    public int precioFinal() {
        return precioBase;
    }

    public String describir() {
        return nombre;
    }
}
