# Respuestas de evaluaciones - Seguimiento 1 Corte 3

## Nivel 01 - Clases

**¿Qué es una clase?**
Es una plantilla o modelo que define los atributos (estado) y los métodos (comportamiento) de un tipo de objeto. No ocupa memoria por sí misma; sirve para crear instancias.

**¿Qué es un objeto?**
Es una instancia de una clase. Cada objeto tiene su propio estado y comparte la estructura y el comportamiento definidos por su clase.

**¿Qué relación hay entre `Cliente`, `Mesa` y `Barista`?**
`Mesa` puede alojar a un `Cliente` y `Barista` prepara bebidas; cada clase modela una responsabilidad distinta del dominio de la cafetería y se comunica con las demás mediante sus métodos públicos.

## Nivel 02 - Encapsulamiento

**¿Qué es el encapsulamiento?**
Es el mecanismo que oculta el estado interno de un objeto y expone solo una interfaz controlada para acceder a él o modificarlo.

**¿Por qué se usan atributos `private` y métodos públicos?**
Porque evita que código externo deje el objeto en un estado inválido. Los `setters` pueden validar los datos antes de asignarlos, por ejemplo rechazando un precio negativo.

**¿Qué ventajas aporta?**
Protege la integridad de los datos, reduce el acoplamiento entre clases y permite cambiar la implementación interna sin afectar a quienes usan la clase.

## Nivel 03 - Constructores

**¿Qué es un constructor?**
Es un bloque especial que se ejecuta al crear un objeto para inicializar sus atributos. Tiene el mismo nombre que la clase y no declara tipo de retorno.

**¿Qué es la sobrecarga de constructores?**
Es definir varios constructores con distintas listas de parámetros. Permite crear objetos con distintos niveles de detalle.

**¿Para qué sirve `this(...)`?**
Para invocar a otro constructor de la misma clase desde el primero, reutilizando la lógica de inicialización y evitando duplicar código.

**¿Qué es un constructor de copia?**
Es un constructor que recibe otra instancia de la misma clase y crea un nuevo objeto con los mismos valores, como `Reserva(Reserva original)`.

## Nivel 04 - Herencia

**¿Qué es el polimorfismo?**
Es la capacidad de tratar objetos de clases distintas como si fueran del mismo tipo base, de modo que cada uno responde con su propia implementación. En el proyecto, `List<Bebida>` contiene `BebidaCaliente`, `BebidaFria` y `Licuado`; al recorrerla, `bebida.precioFinal()` y `bebida.describir()` ejecutan la versión correspondiente a cada objeto sin necesidad de condicionales.

**¿Para qué sirve `super`?**
Sirve para invocar al constructor de la superclase (debe ser la primera instrucción del constructor hijo) y para acceder a sus miembros visibles. `BebidaCaliente`, `BebidaFria` y `Licuado` llaman a `super(nombre, precioBase)` para inicializar los atributos `protected` definidos en `Bebida`, y sus `describir()` reutilizan `super.describir()` para añadir su detalle.

**¿Qué garantiza `@Override`?**
Indica explícitamente que el método sobrescribe uno de la jerarquía. El compilador verifica que la firma coincida, evitando errores silenciosos por un nombre o parámetros mal escritos.

**¿Por qué `nombre` y `precioBase` son `protected`?**
Porque las subclases necesitan leerlos para calcular su precio final, pero no deben quedar expuestos a código externo: se mantiene el encapsulamiento frente al exterior y se permite la especialización dentro de la jerarquía.

**Herencia frente a composición**
La herencia modela una relación "es un": una `BebidaCaliente` es una `Bebida`. La composición modela "tiene un", como un `Licuado` que tiene una lista de frutas. Se usa composición cuando la relación no es una especialización real.

## Nivel 05 - Interfaces

**¿Qué es una interfaz?**
Es un contrato: declara qué operaciones debe ofrecer una clase, sin imponer cómo almacenar el estado ni cómo implementarlas. `Facturable` exige `subtotal()` y aporta `impuesto()` y `total()` como métodos `default`.

**¿Cuál es la diferencia entre una interfaz y una clase abstracta?**

| Aspecto | Interfaz | Clase abstracta |
| --- | --- | --- |
| Estado | Solo constantes `static final` | Campos de instancia normales |
| Constructores | No tiene | Puede tener |
| Herencia | Una clase implementa varias | Una clase extiende solo una |
| Métodos | Abstractos, `default`, `static` y `private` | Abstractos y concretos |
| Propósito | Definir un contrato de comportamiento | Compartir una base parcial entre clases emparentadas |

**¿Qué aportan los métodos `default`?**
Permiten evolucionar una interfaz sin romper a quienes ya la implementan: `impuesto()` calcula el 8% y `total()` suma `subtotal()` más `impuesto()`; ninguna orden necesita reescribirlos.

**¿Cómo se ven varias implementaciones en el proyecto?**
`OrdenMesa implements Facturable, Descontable` aplica un descuento validado entre 0 y 50, mientras `OrdenDomicilio implements Facturable` incluye los $4.000 de envío en su `subtotal()`. Ambas conviven en un `List<Facturable>` y se facturan con el mismo código.

## Nivel 06 - Enums

**¿Qué ventajas tienen los `enum` frente a constantes `String` o `int`?**
- **Seguridad de tipos:** un método que recibe `TamanoVaso` solo admite `PEQUENO`, `MEDIANO` o `GRANDE`; con `String` cualquier cadena, incluso `"grandee"`, sería aceptada.
- **Dominio cerrado:** `EstadoPedido` enumera los únicos estados válidos, evitando valores arbitrarios como el entero `7`.
- **Datos asociados:** cada constante lleva sus onzas y su multiplicador de precio; con constantes `int` habría que mantener tablas separadas.
- **Comportamiento:** `calcularPrecio(int)` y `puedeTransicionarA(EstadoPedido)` concentran las reglas junto a los valores que las definen.
- **Switch expression:** `case RECIBIDO -> ...` es exhaustivo; el compilador avisa si falta un estado, algo imposible de garantizar con cadenas.

**¿Cómo se aplica en el proyecto?**
`TamanoVaso.MEDIANO.calcularPrecio(6000)` devuelve $7.500 usando su multiplicador 1.25. `PedidoCafe.avanzarA(...)` usa `EstadoPedido.puedeTransicionarA(...)`: permite `RECIBIDO -> EN_PREPARACION`, pero rechaza `RECIBIDO -> ENTREGADO` y cualquier cambio sobre `CANCELADO` con `IllegalStateException`.

## Nivel 07 - SOLID

**SRP (Single Responsibility Principle).** Una clase debe tener una sola razón para cambiar. `CalculadorTicket` calcula descuentos, impuestos y totales; `ImpresoraRecibo` solo formatea y muestra el recibo con `IO.println`; `ProcesadorVentas` coordina el flujo. Cambiar el formato del recibo no obliga a modificar los cálculos.

**OCP (Open/Closed Principle).** El código debe estar abierto a extensión y cerrado a modificación. Para añadir `PagoNequi` no hizo falta tocar `ProcesadorVentas`: basta con crear una clase que implemente `MetodoPago`. Lo mismo ocurre con `PagoEfectivo` y `PagoTarjeta`.

**DIP (Dependency Inversion Principle).** Los módulos de alto nivel no deben depender de implementaciones concretas, sino de abstracciones. `ProcesadorVentas` recibe `MetodoPago` por inyección en su constructor; nunca menciona a `PagoEfectivo`, `PagoTarjeta` ni `PagoNequi`. Por eso el mismo procesador atiende las tres formas de pago sin una sola línea de `if`.

**Beneficio conjunto.** Menos acoplamiento, pruebas más sencillas (se puede inyectar un pago falso) y cambios locales: agregar un método de pago, ajustar el impuesto o rediseñar el recibo son modificaciones independientes.

## Nivel 08 - Excepciones

**¿Qué diferencia hay entre `Error` y `Exception`?**
Un `Error` indica un problema grave de la JVM que normalmente no se captura; una `Exception` representa una condición que la aplicación puede manejar.

**¿Qué son excepciones comprobadas y no comprobadas?**
Las comprobadas heredan de `Exception` (excepto `RuntimeException`): el compilador obliga a declararlas con `throws` o a capturarlas. `StockInsuficienteException` es comprobada porque falta de stock es una situación de negocio esperable que `CajaRegistradora.cobrar(...)` debe comunicar. Las no comprobadas heredan de `RuntimeException`: `CajaCerradaException` indica un error de uso del sistema (cobrar sin abrir turno), que se corregía antes de ejecutar, por eso no se fuerza su manejo.

**¿Para qué sirven `try`, `catch`, `finally` y `throw`?**
`try` delimita el código riesgo, `catch` maneja la excepción, `finally` ejecuta limpieza siempre y `throw` lanza explícitamente una excepción. En el `Main` se captura `StockInsuficienteException` dentro del turno, se informa del producto y las unidades, y la caja sigue operando con la siguiente venta.

**¿Qué ventaja ofrecen `AutoCloseable` y `try-with-resources`?**
`CajaRegistradora implements AutoCloseable` permite escribir `try (CajaRegistradora caja = new CajaRegistradora()) { ... }`. El bloque invoca `close()` automáticamente al terminar, tanto si todo salió bien como si ocurrió una excepción; por eso el arqueo final se imprime sin llamadas explícitas. Evita olvidar la liberación de recursos y reduce el código frente a un `finally` manual.

## Nivel 09 - Colecciones

**¿Cuándo elegir `List`, `Set` o `Map`?**
- `List`: cuando importa el orden y pueden repetirse elementos; por ejemplo la lista de ventas que se recorre con streams para filtrar y agrupar.
- `Set`: cuando solo interesa saber qué valores existen, sin duplicados; `AnalizadorVentas.categoriasUnicas()` devuelve un `Set<String>` de las categorías vendidas.
- `Map`: cuando se necesita asociar una clave con un valor; `totalPorCategoria()` devuelve un `Map<String, Integer>` con el dinero recaudado por cada categoría.

**¿Cuándo usar `ArrayList` o `LinkedList`?**
`ArrayList` es mejor para acceso aleatorio frecuente; `LinkedList` conviene cuando se insertan o eliminan elementos de forma constante en posiciones conocidas.

**¿Qué ventajas tiene un `record` en Java 25?**
`record ItemVenta(...)` declara en una línea los campos, el constructor canónico, los accesadores, `equals`, `hashCode` y `toString`. El constructor compacto permite validar cantidad y precio sin escribir asignaciones repetitivas. Al ser inmutable por defecto, es ideal para transportar datos (ventas, productos) de forma segura y con menos código.

**¿Qué aportan los streams y los genéricos?**
Los streams permiten expresar filtros, mapas, agrupaciones (`Collectors.groupingBy` con `Collectors.summingInt`) y búsquedas (`max` devolviendo `Optional`) de forma declarativa. Los genéricos fijan el tipo de los elementos en compilación, evitando conversiones y errores en tiempo de ejecución.

## Nivel 10 - Serialización

**¿Qué es serializar un objeto?**
Es convertir su estado en un formato persistente para almacenarlo o enviarlo, y reconstruirlo después. Aquí se usa un CSV ligero en lugar del formato binario de `Serializable`.

**¿Cómo mejora `java.nio.file` frente a `java.io` clásico?**
`Path` y `Files` ofrecen operaciones directas: `Files.createDirectories` crea `data/` si no existe, `Files.write` escribe todas las líneas de una vez y `Files.readAllLines` las lee en una sola llamada. Además se especifica explícitamente `StandardCharsets.UTF_8`, se lanza `IOException` con información clara y no hay que gestionar manualmente `FileOutputStream` o `BufferedReader`. El código queda más corto, portable y seguro.

**¿Cómo se valida un archivo corrupt?**
`cargar()` comprueba que cada línea tenga cuatro campos, que el precio y el stock sean enteros y que no sean negativos. Si algo falla, lanza una `IOException` indicando el número de línea, de modo que `Main` pueda informar del error sin dejar objetos inválidos en memoria.

## Nivel 11 - Proyecto final

**¿Cómo se organiza el sistema "Café de Barrio POS"?**
En modelos inmutables (`ProductoMenu`, `LineaPedido`, `Pedido`, `Tamano`, `MetodoPago`), servicios con una sola responsabilidad (`InventarioService`, `PagoService`, `ReporteService`) y la excepción de negocio `PedidoInvalidoException`. `InventarioService` persiste en `data/menu_final.csv`; `PagoService` delega en estrategias de pago; `ReporteService` usa streams para agrupar ventas por categoría.

**¿Cómo se ven aplicados los conceptos del curso?**
Herencia e interfaces en las estrategias de pago, enums para tamaños y métodos, excepciones para rechazar compras sin stock, colecciones y streams para el reporte, y `java.nio.file` para conservar el menú entre jornadas. La simulación del `Main` carga el menú, registra tres pedidos con tamaños y pagos distintos, rechaza una compra sin stock, muestra el informe del día y guarda el inventario actualizado.

**¿Cómo se comprueba que el diseño es correcto?**
Con código legible, responsabilidades separadas, pruebas de los escenarios principales y ausencia de duplicación innecesaria.
