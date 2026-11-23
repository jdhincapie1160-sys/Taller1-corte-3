package n11_proyecto_final;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class PagoService {
    public record ResultadoPago(MetodoPago metodo, int monto, String mensaje) {
    }

    private final Map<MetodoPago, EstrategiaPago> estrategias;

    public PagoService() {
        this(new EnumMap<>(MetodoPago.class));
        estrategias.put(MetodoPago.EFECTIVO, monto -> "Efectivo: $" + monto + " aprobado sin recargo");
        estrategias.put(MetodoPago.TARJETA,
                monto -> "Tarjeta: $" + (monto * 102 / 100) + " aprobado (2% de recargo)");
        estrategias.put(MetodoPago.NEQUI, monto -> {
            if (monto < 1000) {
                throw new IllegalArgumentException("Nequi requiere un monto mínimo de $1000");
            }
            return "Nequi: $" + monto + " aprobado sin recargo";
        });
    }

    public PagoService(Map<MetodoPago, EstrategiaPago> estrategias) {
        this.estrategias = Objects.requireNonNull(estrategias, "Las estrategias son obligatorias");
    }

    public ResultadoPago pagar(MetodoPago metodo, int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        EstrategiaPago estrategia = estrategias.get(metodo);
        if (estrategia == null) {
            throw new IllegalArgumentException("Método de pago no soportado: " + metodo);
        }
        return new ResultadoPago(metodo, monto, estrategia.procesar(monto));
    }
}
