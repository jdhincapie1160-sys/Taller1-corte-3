package n03_constructores;

import java.math.BigDecimal;
import java.time.LocalDate;

class Main {
    void main() {
        Reserva reservaCompleta = new Reserva("RES-001", LocalDate.of(2026, 12, 24), 4);
        Reserva reservaHoy = new Reserva("RES-002", 2);
        Reserva reservaCopia = new Reserva(reservaCompleta);
        IO.println(reservaCompleta);
        IO.println(reservaHoy);
        IO.println(reservaCopia);

        Postre tiramisu = new Postre("Tiramisú", new BigDecimal("5.00"));
        Postre brownie = new Postre("Brownie");
        Postre copia = new Postre(tiramisu);
        IO.println(tiramisu);
        IO.println(brownie);
        IO.println(copia);

        IO.println(new Combo("Combo tarde", brownie));
        IO.println(new Combo("Combo noche", "Té chai", tiramisu));
        IO.println(new Combo("Personalizado", "Espresso", copia, new BigDecimal("6.75")));
    }
}
