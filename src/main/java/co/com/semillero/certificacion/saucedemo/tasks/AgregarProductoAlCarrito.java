package co.com.semillero.certificacion.saucedemo.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeInventario.BOTON_AGREGAR_PRODUCTO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Capa TASKS: tarea de negocio "agregar un producto al carrito" buscándolo por su nombre.
 * POO - POLIMORFISMO: implementa la interfaz Task.
 */
public class AgregarProductoAlCarrito implements Task {

    /** Nombre visible del producto, tal como aparece en la tienda. */
    private final String nombreDelProducto;

    /** Constructor público: guarda el nombre del producto. */
    public AgregarProductoAlCarrito(String nombreDelProducto) {
        this.nombreDelProducto = nombreDelProducto;
    }

    /** Hace clic en el botón "Add to cart" del producto (Target dinámico con .of(...)). */
    @Override
    @Step("{0} agrega el producto #nombreDelProducto al carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BOTON_AGREGAR_PRODUCTO.of(nombreDelProducto))
        );
    }

    /** Método de fábrica legible: AgregarProductoAlCarrito.llamado("Sauce Labs Backpack"). */
    public static AgregarProductoAlCarrito llamado(String nombreDelProducto) {
        return instrumented(AgregarProductoAlCarrito.class, nombreDelProducto);
    }
}
