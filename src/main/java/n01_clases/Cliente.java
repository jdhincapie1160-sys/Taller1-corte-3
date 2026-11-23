package n01_clases;

public class Cliente {
    private final String nombre;
    private int visitas;

    public Cliente(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String nombre() {
        return nombre;
    }

    public int visitas() {
        return visitas;
    }

    void registrarVisita() {
        visitas++;
    }

    @Override
    public String toString() {
        return "Cliente{nombre='" + nombre + "', visitas=" + visitas + "}";
    }
}
