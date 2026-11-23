package n08_excepciones;

public class CajaCerradaException extends RuntimeException {
    public CajaCerradaException(String mensaje) {
        super(mensaje);
    }
}
