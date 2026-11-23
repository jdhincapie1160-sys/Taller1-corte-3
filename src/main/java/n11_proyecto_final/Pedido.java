package n11_proyecto_final;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final int numero;
    private final MetodoPago metodoPago;
    private final List<LineaPedido> lineas = new ArrayList<>();

    public Pedido(int numero, MetodoPago metodoPago) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de pedido debe ser positivo");
        }
        if (metodoPago == null) {
            throw new IllegalArgumentException("El método de pago es obligatorio");
        }
        this.numero = numero;
        this.metodoPago = metodoPago;
    }

    public int numero() {
        return numero;
    }

    public MetodoPago metodoPago() {
        return metodoPago;
    }

    public List<LineaPedido> lineas() {
        return List.copyOf(lineas);
    }

    public void agregar(LineaPedido linea) {
        if (linea == null) {
            throw new IllegalArgumentException("La línea de pedido es obligatoria");
        }
        lineas.add(linea);
    }

    public int subtotal() {
        return lineas.stream().mapToInt(LineaPedido::subtotal).sum();
    }

    @Override
    public String toString() {
        return "Pedido{numero=" + numero + ", metodo=" + metodoPago
                + ", lineas=" + lineas + ", subtotal=" + subtotal() + "}";
    }
}
