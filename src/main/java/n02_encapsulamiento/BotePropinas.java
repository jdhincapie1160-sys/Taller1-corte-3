package n02_encapsulamiento;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BotePropinas {
    private BigDecimal total = BigDecimal.ZERO;

    public void agregar(BigDecimal propina) {
        if (propina == null || propina.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La propina debe ser positiva");
        }
        total = total.add(propina);
    }

    public BigDecimal total() {
        return total;
    }

    public BigDecimal[] repartirEntre(int personas) {
        if (personas <= 0) {
            throw new IllegalArgumentException("El número de personas debe ser positivo");
        }
        BigDecimal parte = total.divide(BigDecimal.valueOf(personas), 2, RoundingMode.DOWN);
        BigDecimal[] resultado = new BigDecimal[personas];
        BigDecimal restante = total;
        for (int i = 0; i < personas; i++) {
            BigDecimal asignacion = i == personas - 1 ? restante : parte;
            resultado[i] = asignacion;
            restante = restante.subtract(asignacion);
        }
        total = BigDecimal.ZERO;
        return resultado;
    }

    @Override
    public String toString() {
        return "BotePropinas{total=" + total + "}";
    }
}
