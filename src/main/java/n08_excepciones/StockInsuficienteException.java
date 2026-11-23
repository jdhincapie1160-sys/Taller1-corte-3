package n08_excepciones;

public class StockInsuficienteException extends Exception {
    private final String producto;
    private final int stockDisponible;
    private final int unidadesSolicitadas;

    public StockInsuficienteException(String producto, int stockDisponible, int unidadesSolicitadas) {
        super("Stock insuficiente para " + producto + ": se solicitaron " + unidadesSolicitadas
                + " unidades y solo hay " + stockDisponible);
        this.producto = producto;
        this.stockDisponible = stockDisponible;
        this.unidadesSolicitadas = unidadesSolicitadas;
    }

    public String producto() {
        return producto;
    }

    public int stockDisponible() {
        return stockDisponible;
    }

    public int unidadesSolicitadas() {
        return unidadesSolicitadas;
    }
}
