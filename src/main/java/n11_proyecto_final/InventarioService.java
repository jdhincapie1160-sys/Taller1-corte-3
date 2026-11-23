package n11_proyecto_final;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventarioService {
    private static final Path ARCHIVO = Path.of("data", "menu_final.csv");

    private final List<ProductoMenu> productos = new ArrayList<>();

    public Path ruta() {
        return ARCHIVO;
    }

    public void cargar() throws IOException {
        productos.clear();
        if (!Files.exists(ARCHIVO)) {
            productos.addAll(menuInicial());
            return;
        }

        List<String> lineas = Files.readAllLines(ARCHIVO, StandardCharsets.UTF_8);
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.isBlank()) {
                continue;
            }
            String[] campos = linea.split(",", -1);
            if (campos.length != 5) {
                throw new IOException("Línea corrupta " + (i + 1) + " en el menú");
            }
            try {
                int precio = Integer.parseInt(campos[3].trim());
                int stock = Integer.parseInt(campos[4].trim());
                productos.add(new ProductoMenu(campos[0].trim(), campos[1].trim(),
                        campos[2].trim(), precio, stock));
            } catch (NumberFormatException e) {
                throw new IOException("Línea corrupta " + (i + 1) + " en el menú", e);
            }
        }
    }

    public void guardar() throws IOException {
        Files.createDirectories(ARCHIVO.getParent());
        List<String> lineas = productos.stream()
                .map(producto -> producto.codigo() + "," + producto.nombre() + ","
                        + producto.categoria() + "," + producto.precio() + "," + producto.stock())
                .toList();
        Files.write(ARCHIVO, lineas, StandardCharsets.UTF_8);
    }

    public List<ProductoMenu> productos() {
        return List.copyOf(productos);
    }

    public Optional<ProductoMenu> buscar(String codigo) {
        return productos.stream()
                .filter(producto -> producto.codigo().equals(codigo))
                .findFirst();
    }

    public void reservar(String codigo, int cantidad) throws PedidoInvalidoException {
        ProductoMenu producto = buscar(codigo)
                .orElseThrow(() -> new PedidoInvalidoException("Producto no encontrado: " + codigo));
        if (cantidad <= 0) {
            throw new PedidoInvalidoException("La cantidad debe ser positiva");
        }
        if (producto.stock() < cantidad) {
            throw new PedidoInvalidoException("Sin stock de " + producto.nombre() + ": se pidieron "
                    + cantidad + " unidades y hay " + producto.stock());
        }
        productos.set(productos.indexOf(producto), producto.conStock(producto.stock() - cantidad));
    }

    private List<ProductoMenu> menuInicial() {
        return List.of(
                new ProductoMenu("CAP-01", "Café americano", "Bebidas Calientes", 4500, 20),
                new ProductoMenu("LAT-01", "Café latte", "Bebidas Calientes", 5500, 15),
                new ProductoMenu("LIM-01", "Limonada", "Bebidas Frías", 5500, 12),
                new ProductoMenu("BRO-01", "Brownie", "Repostería", 3500, 8),
                new ProductoMenu("TIR-01", "Tiramisú", "Repostería", 5000, 6)
        );
    }
}
