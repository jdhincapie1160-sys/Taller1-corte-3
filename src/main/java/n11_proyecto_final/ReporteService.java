package n11_proyecto_final;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ReporteService {
    private final List<Pedido> pedidos;

    public ReporteService(List<Pedido> pedidos) {
        this.pedidos = List.copyOf(pedidos);
    }

    public Map<String, Integer> ventasPorCategoria() {
        return pedidos.stream()
                .flatMap(pedido -> pedido.lineas().stream())
                .collect(Collectors.groupingBy(
                        linea -> linea.producto().categoria(),
                        TreeMap::new,
                        Collectors.summingInt(LineaPedido::subtotal)));
    }

    public int totalGeneral() {
        return pedidos.stream().mapToInt(Pedido::subtotal).sum();
    }

    public Map<MetodoPago, Long> pedidosPorMetodo() {
        return pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::metodoPago, Collectors.counting()));
    }
}
