package n04_herencia;

import java.util.List;

public class Licuado extends Bebida {
    private static final int PRECIO_FRUTA_ADICIONAL = 1000;

    private final List<String> frutas;

    public Licuado(String nombre, int precioBase, List<String> frutas) {
        super(nombre, precioBase);
        if (frutas == null || frutas.isEmpty()) {
            throw new IllegalArgumentException("El licuado debe tener al menos una fruta");
        }
        if (frutas.stream().anyMatch(fruta -> fruta == null || fruta.isBlank())) {
            throw new IllegalArgumentException("Las frutas no pueden estar vacías");
        }
        this.frutas = List.copyOf(frutas);
    }

    public List<String> frutas() {
        return frutas;
    }

    @Override
    public int precioFinal() {
        return precioBase + Math.max(0, frutas.size() - 1) * PRECIO_FRUTA_ADICIONAL;
    }

    @Override
    public String describir() {
        return super.describir() + " (licuado de " + String.join(", ", frutas) + ")";
    }
}
