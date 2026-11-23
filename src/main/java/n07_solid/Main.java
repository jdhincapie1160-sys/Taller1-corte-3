package n07_solid;

class Main {
    void main() {
        int montoVenta = 25000;
        MetodoPago[] metodos = {
                new PagoEfectivo(),
                new PagoTarjeta(),
                new PagoNequi()
        };

        for (MetodoPago metodo : metodos) {
            ProcesadorVentas procesador = new ProcesadorVentas(metodo);
            procesador.procesar("Café de Barrio", montoVenta, 10);
        }

        try {
            new ProcesadorVentas(new PagoNequi()).procesar("Venta pequeña", 500, 0);
        } catch (IllegalArgumentException e) {
            IO.println("Pago rechazado: " + e.getMessage());
        }
    }
}
