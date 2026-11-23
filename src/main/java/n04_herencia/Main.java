package n04_herencia;

import java.util.List;

class Main {
    void main() {
        List<Bebida> cuenta = List.of(
                new BebidaCaliente("Café americano", 4500, false),
                new BebidaCaliente("Café con leche", 5000, true),
                new BebidaFria("Limonada", 5500, "mediano"),
                new BebidaFria("Té helado", 6000, "grande"),
                new Licuado("Licuado", 7000, List.of("fresa", "banana", "mango"))
        );

        int total = 0;
        for (Bebida bebida : cuenta) {
            int precio = bebida.precioFinal();
            total += precio;
            IO.println(bebida.describir() + " -> $" + precio);
        }
        IO.println("Total de la cuenta: $" + total);
    }
}
