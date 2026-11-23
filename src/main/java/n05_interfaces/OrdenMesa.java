package n05_interfaces;

public class OrdenMesa implements Facturable, Descontable {
    private final int numeroMesa;
    private final int montoBase;
    private int porcentajeDescuento;

    public OrdenMesa(int numeroMesa, int montoBase) {
        if (numeroMesa <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser positivo");
        }
        if (montoBase < 0) {
            throw new IllegalArgumentException("El monto base no puede ser negativo");
        }
        this.numeroMesa = numeroMesa;
        this.montoBase = montoBase;
    }

    public int numeroMesa() {
        return numeroMesa;
    }

    public int montoBase() {
        return montoBase;
    }

    public int porcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public int subtotal() {
        return montoBase - montoBase * porcentajeDescuento / 100;
    }

    @Override
    public int aplicarDescuento(int porcentaje) {
        if (porcentaje < 0 || porcentaje > 50) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 50");
        }
        this.porcentajeDescuento = porcentaje;
        return total();
    }

    @Override
    public String toString() {
        return "OrdenMesa{mesa=" + numeroMesa + ", montoBase=" + montoBase + ", descuento=" + porcentajeDescuento + "%}";
    }
}
