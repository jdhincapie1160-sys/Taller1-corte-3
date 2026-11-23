package n08_excepciones;

class Main {
    void main() throws StockInsuficienteException {
        CajaRegistradora caja = new CajaRegistradora();

        try (CajaRegistradora turno = caja) {
            turno.abrir();
            turno.cobrar("Café americano", 10, 2, 4500);
            try {
                turno.cobrar("Croissant", 1, 3, 3200);
            } catch (StockInsuficienteException e) {
                IO.println("Venta rechazada: " + e.getMessage());
                IO.println("  producto=" + e.producto() + ", solicitadas=" + e.unidadesSolicitadas()
                        + ", disponibles=" + e.stockDisponible());
            }
            turno.cobrar("Limonada", 8, 1, 5500);
        }

        try {
            caja.cobrar("Té helado", 5, 1, 6000);
        } catch (CajaCerradaException e) {
            IO.println("Error de caja: " + e.getMessage());
        }
    }
}
