package co.com.semillero.certificacion.dummyjson.tasks;

import co.com.semillero.certificacion.dummyjson.utils.Rutas;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

// Tarea: consultar un producto por su id con un GET.
public class ConsultarProducto implements Task {

    // Número del producto que se va a consultar.
    private final String id;

    // Serenity usa este constructor cuando crea la tarea con instrumented(...).
    public ConsultarProducto(String id) {
        this.id = id;
    }

    // Forma cómoda de crear la tarea desde el step: ConsultarProducto.conId("1")
    public static ConsultarProducto conId(String id) {
        return instrumented(ConsultarProducto.class, id);
    }

    // Lo que hace el actor: un GET a /products/{id}, cambiando {id} por el número del producto.
    @Override
    @Step("{0} consulta el producto con id #id")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Rutas.PRODUCTO).with(peticion -> peticion.pathParam("id", id))
        );
    }
}
