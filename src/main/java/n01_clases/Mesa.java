package n01_clases;

public class Mesa {
    private final int numero;
    private final int capacidad;
    private Cliente cliente;

    public Mesa(int numero, int capacidad) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser positivo");
        }
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva");
        }
        this.numero = numero;
        this.capacidad = capacidad;
    }

    public int numero() {
        return numero;
    }

    public int capacidad() {
        return capacidad;
    }

    public boolean estaOcupada() {
        return cliente != null;
    }

    public boolean sentar(Cliente cliente) {
        if (cliente == null || estaOcupada()) {
            return false;
        }
        this.cliente = cliente;
        cliente.registrarVisita();
        return true;
    }

    public Cliente liberar() {
        Cliente saliente = cliente;
        cliente = null;
        return saliente;
    }

    @Override
    public String toString() {
        String estado = cliente == null ? "libre" : cliente.nombre();
        return "Mesa{numero=" + numero + ", capacidad=" + capacidad + ", cliente=" + estado + "}";
    }
}
