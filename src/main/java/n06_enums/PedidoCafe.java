package n06_enums;

import java.util.Objects;

public class PedidoCafe {
    private final String codigo;
    private final int precioBase;
    private final TamanoVaso tamano;
    private EstadoPedido estado;

    public PedidoCafe(String codigo, int precioBase, TamanoVaso tamano) {
        this.codigo = Objects.requireNonNull(codigo, "El código es obligatorio");
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo");
        }
        this.precioBase = precioBase;
        this.tamano = Objects.requireNonNull(tamano, "El tamaño es obligatorio");
        this.estado = EstadoPedido.RECIBIDO;
    }

    public String codigo() {
        return codigo;
    }

    public int precioBase() {
        return precioBase;
    }

    public TamanoVaso tamano() {
        return tamano;
    }

    public EstadoPedido estado() {
        return estado;
    }

    public int precio() {
        return tamano.calcularPrecio(precioBase);
    }

    public void avanzarA(EstadoPedido nuevo) {
        if (nuevo == null) {
            throw new IllegalArgumentException("El estado nuevo es obligatorio");
        }
        if (estado == EstadoPedido.CANCELADO) {
            throw new IllegalStateException("No se puede modificar un pedido cancelado");
        }
        if (!estado.puedeTransicionarA(nuevo)) {
            throw new IllegalStateException("No se puede pasar de " + estado + " a " + nuevo);
        }
        estado = nuevo;
    }

    @Override
    public String toString() {
        return "PedidoCafe{codigo='" + codigo + "', tamano=" + tamano + ", estado=" + estado + ", precio=" + precio() + "}";
    }
}
