package n04_herencia;

public class BebidaCaliente extends Bebida {
    private static final int RECARGO_LECHE = 1500;

    private final boolean conLeche;

    public BebidaCaliente(String nombre, int precioBase, boolean conLeche) {
        super(nombre, precioBase);
        this.conLeche = conLeche;
    }

    public boolean conLeche() {
        return conLeche;
    }

    @Override
    public int precioFinal() {
        return precioBase + (conLeche ? RECARGO_LECHE : 0);
    }

    @Override
    public String describir() {
        return super.describir() + (conLeche ? " (caliente con leche)" : " (caliente sin leche)");
    }
}
