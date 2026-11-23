package n02_encapsulamiento;

import java.math.BigDecimal;
import java.util.Objects;

public class TarjetaRegalo {
    private final String codigo;
    private BigDecimal saldo;

    public TarjetaRegalo(String codigo, BigDecimal saldoInicial) {
        this.codigo = Objects.requireNonNull(codigo, "El código es obligatorio");
        if (saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.saldo = saldoInicial;
    }

    public String codigo() {
        return codigo;
    }

    public BigDecimal saldo() {
        return saldo;
    }

    public boolean pagar(BigDecimal monto) {
        validarMonto(monto);
        if (monto.compareTo(saldo) > 0) {
            return false;
        }
        saldo = saldo.subtract(monto);
        return true;
    }

    public void recargar(BigDecimal monto) {
        validarMonto(monto);
        saldo = saldo.add(monto);
    }

    private void validarMonto(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
    }

    @Override
    public String toString() {
        return "TarjetaRegalo{codigo='" + codigo + "', saldo=" + saldo + "}";
    }
}
