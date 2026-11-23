package n01_clases;

class Main {
    void main() {
        Cliente ana = new Cliente("Ana");
        Cliente luis = new Cliente("Luis");
        Mesa mesa = new Mesa(4, 2);
        Barista barista = new Barista("Lucas");

        IO.println(mesa);
        IO.println("Sentar a Ana: " + mesa.sentar(ana));
        IO.println("Sentar a Luis: " + mesa.sentar(luis));
        IO.println(barista.preparar("capuchino"));
        IO.println(mesa);
        IO.println(ana);

        Cliente saliente = mesa.liberar();
        IO.println("Mesa liberada por " + saliente.nombre() + ": " + mesa);
        IO.println("Sentar a Luis: " + mesa.sentar(luis));
        IO.println(barista.preparar("flat white"));
        IO.println(barista);
        IO.println(luis);
    }
}
