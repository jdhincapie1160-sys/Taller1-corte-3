package n06_enums;

public enum EstadoPedido {
    RECIBIDO,
    EN_PREPARACION,
    LISTO,
    ENTREGADO,
    CANCELADO;

    public boolean puedeTransicionarA(EstadoPedido nuevo) {
        return switch (this) {
            case RECIBIDO -> nuevo == EN_PREPARACION || nuevo == CANCELADO;
            case EN_PREPARACION -> nuevo == LISTO || nuevo == CANCELADO;
            case LISTO -> nuevo == ENTREGADO;
            case ENTREGADO, CANCELADO -> false;
        };
    }
}
