package n01_clases;

public class Barista {
    private final String nombre;
    private int bebidasPreparadas;

    public Barista(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del barista no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String nombre() {
        return nombre;
    }

    public int bebidasPreparadas() {
        return bebidasPreparadas;
    }

    public String preparar(String bebida) {
        if (bebida == null || bebida.isBlank()) {
            throw new IllegalArgumentException("La bebida no puede estar vacía");
        }
        bebidasPreparadas++;
        return nombre + " preparó " + bebida;
    }

    @Override
    public String toString() {
        return "Barista{nombre='" + nombre + "', bebidasPreparadas=" + bebidasPreparadas + "}";
    }
}
