package n09_colecciones;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class AnalizadorVentas {
    private final List<ItemVenta> ventas;

    public AnalizadorVentas(List<ItemVenta> ventas) {
        this.ventas = List.copyOf(ventas);
    }

    public List<ItemVenta> filtrarPorMontoMinimo(int minimo) {
        return ventas.stream()
                .filter(venta -> venta.subtotal() > minimo)
                .toList();
    }

    public Set<String> categoriasUnicas() {
        return ventas.stream()
                .map(ItemVenta::categoria)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Map<String, Integer> totalPorCategoria() {
        return ventas.stream()
                .collect(Collectors.groupingBy(
                        ItemVenta::categoria,
                        TreeMap::new,
                        Collectors.summingInt(ItemVenta::subtotal)));
    }

    public Optional<ItemVenta> productoMasVendido() {
        return ventas.stream().max(Comparator.comparingInt(ItemVenta::cantidad));
    }

    public Optional<ItemVenta> productoMayorIngreso() {
        return ventas.stream().max(Comparator.comparingInt(ItemVenta::subtotal));
    }
}
