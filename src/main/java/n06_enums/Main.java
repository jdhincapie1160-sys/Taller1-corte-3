package n06_enums;

class Main {
    void main() {
        PedidoCafe pedido = new PedidoCafe("PED-001", 6000, TamanoVaso.MEDIANO);
        IO.println(pedido);

        pedido.avanzarA(EstadoPedido.EN_PREPARACION);
        IO.println("Tras preparación: " + pedido.estado());
        pedido.avanzarA(EstadoPedido.LISTO);
        IO.println("Tras listo: " + pedido.estado());
        pedido.avanzarA(EstadoPedido.ENTREGADO);
        IO.println("Tras entrega: " + pedido.estado());

        IO.println("Precios según tamaño (base $6000):");
        for (TamanoVaso tamano : TamanoVaso.values()) {
            IO.println("  " + tamano + " (" + tamano.onzas() + " oz): $" + tamano.calcularPrecio(6000));
        }

        try {
            PedidoCafe invalido = new PedidoCafe("PED-002", 5000, TamanoVaso.PEQUENO);
            invalido.avanzarA(EstadoPedido.ENTREGADO);
        } catch (IllegalStateException e) {
            IO.println("Transición rechazada: " + e.getMessage());
        }
    }
}
