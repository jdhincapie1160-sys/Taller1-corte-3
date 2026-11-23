package n04_herencia;

public class BebidaFria extends Bebida {
    private static final int RECARGO_GRANDE = 2000;

    private final String tamano;

    public BebidaFria(String nombre, int precioBase, String tamano) {
        super(nombre, precioBase);
        if (!"mediano".equals(tamano) && !"grande".equals(tamano)) {
            throw new IllegalArgumentException("El tamaño debe ser 'mediano' o 'grande'");
        }
        this.tamano = tamano;
    }

    public String tamano() {
        return tamano;
    }

    @Override
    public int precioFinal() {
        return precioBase + ("grande".equals(tamano) ? RECARGO_GRANDE : 0);
    }

    @Override
    public String describir() {
        return super.describir() + " (fría " + tamano + ")";
    }
}
