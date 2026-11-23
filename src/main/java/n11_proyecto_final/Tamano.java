package n11_proyecto_final;

public enum Tamano {
    PEQUENO(1.0),
    MEDIANO(1.25),
    GRANDE(1.5);

    private final double multiplicador;

    private Tamano(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double multiplicador() {
        return multiplicador;
    }

    public int calcularPrecio(int precioBase) {
        return (int) Math.round(precioBase * multiplicador);
    }
}
