package n02_encapsulamiento;

import java.math.BigDecimal;
import java.util.Arrays;

class Main {
    void main() {
        Producto cafe = new Producto("Café latte", "Bebidas", new BigDecimal("4.50"));
        Producto croissant = new Producto("Croissant", "Panadería", new BigDecimal("3.25"));
        IO.println(cafe);
        IO.println(croissant);

        cafe.setPrecio(new BigDecimal("4.75"));
        IO.println("Precio actualizado de " + cafe.nombre() + ": " + cafe.precio());

        TarjetaRegalo tarjeta = new TarjetaRegalo("REG-2026-001", new BigDecimal("20.00"));
        IO.println(tarjeta);
        IO.println("Pagar 8.00: " + tarjeta.pagar(new BigDecimal("8.00")));
        IO.println("Pagar 15.00: " + tarjeta.pagar(new BigDecimal("15.00")));
        tarjeta.recargar(new BigDecimal("10.00"));
        IO.println(tarjeta);

        BotePropinas bote = new BotePropinas();
        bote.agregar(new BigDecimal("2.00"));
        bote.agregar(new BigDecimal("1.50"));
        IO.println("Bote antes del reparto: " + bote.total());
        IO.println("Reparto entre 2: " + Arrays.toString(bote.repartirEntre(2)));
        IO.println("Bote después del reparto: " + bote.total());
    }
}
