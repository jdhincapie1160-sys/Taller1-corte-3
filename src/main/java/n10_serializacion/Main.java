package n10_serializacion;

import java.io.IOException;
import java.util.List;

class Main {
    void main() {
        GestorInventarioArchivo gestor = new GestorInventarioArchivo();
        List<ProductoArchivo> productos = List.of(
                new ProductoArchivo("CAP-01", "Café americano", 4500, 20),
                new ProductoArchivo("LAT-01", "Café latte", 5500, 15),
                new ProductoArchivo("LIM-01", "Limonada", 5500, 12),
                new ProductoArchivo("BRO-01", "Brownie", 3500, 8),
                new ProductoArchivo("TIR-01", "Tiramisú", 5000, 6)
        );

        try {
            gestor.guardar(productos);
            IO.println("Guardados " + productos.size() + " productos en " + gestor.ruta());

            List<ProductoArchivo> recuperados = gestor.cargar();
            IO.println("Productos recuperados del disco:");
            recuperados.forEach(producto -> IO.println("  " + producto));
            IO.println("Datos persistentes: " + recuperados.equals(productos));
        } catch (IOException e) {
            IO.println("Error de persistencia: " + e.getMessage());
        }
    }
}
