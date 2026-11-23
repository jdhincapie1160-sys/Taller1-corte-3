package n10_serializacion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorInventarioArchivo {
    private static final Path ARCHIVO = Path.of("data", "inventario_cafe.csv");

    public Path ruta() {
        return ARCHIVO;
    }

    public void guardar(List<ProductoArchivo> productos) throws IOException {
        Files.createDirectories(ARCHIVO.getParent());
        List<String> lineas = productos.stream()
                .map(producto -> producto.codigo() + "," + producto.nombre() + ","
                        + producto.precio() + "," + producto.stock())
                .toList();
        Files.write(ARCHIVO, lineas, StandardCharsets.UTF_8);
    }

    public List<ProductoArchivo> cargar() throws IOException {
        if (!Files.exists(ARCHIVO)) {
            return List.of();
        }

        List<ProductoArchivo> productos = new ArrayList<>();
        List<String> lineas = Files.readAllLines(ARCHIVO, StandardCharsets.UTF_8);
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.isBlank()) {
                continue;
            }
            String[] campos = linea.split(",", -1);
            if (campos.length != 4) {
                throw new IOException("Línea corrupta " + (i + 1) + ": se esperaban 4 campos");
            }
            try {
                String codigo = campos[0].trim();
                String nombre = campos[1].trim();
                int precio = Integer.parseInt(campos[2].trim());
                int stock = Integer.parseInt(campos[3].trim());
                if (codigo.isEmpty() || nombre.isEmpty() || precio < 0 || stock < 0) {
                    throw new IOException("Línea corrupta " + (i + 1) + ": valores inválidos");
                }
                productos.add(new ProductoArchivo(codigo, nombre, precio, stock));
            } catch (NumberFormatException e) {
                throw new IOException("Línea corrupta " + (i + 1) + ": precio o stock no numérico", e);
            }
        }
        return List.copyOf(productos);
    }
}
