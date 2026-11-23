package n05_interfaces;

public interface Facturable {
    int subtotal();

    default int impuesto() {
        return subtotal() * 8 / 100;
    }

    default int total() {
        return subtotal() + impuesto();
    }
}
