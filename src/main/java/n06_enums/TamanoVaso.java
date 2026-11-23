package n06_enums;

public enum TamanoVaso {
    PEQUENO(8, 1.0),
    MEDIANO(12, 1.25),
    GRANDE(16, 1.5);

    private final int onzas;
    private final double multiplicador;

    private TamanoVaso(int onzas, double multiplicador) {
        this.onzas = onzas;
        this.multiplicador = multiplicador;
    }

    public int onzas() {
        return onzas;
    }

    public double multiplicador() {
        return multiplicador;
    }

    public int calcularPrecio(int precioBase) {
        return (int) Math.round(precioBase * multiplicador);
    }
}
