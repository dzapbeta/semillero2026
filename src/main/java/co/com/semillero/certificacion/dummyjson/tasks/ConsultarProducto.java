package co.com.semillero.certificacion.dummyjson.tasks;

import co.com.semillero.certificacion.dummyjson.utils.Endpoints;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * TAREA: consultar un producto por su id (GET /products/{id}).
 * Recibe el id como texto para poder probar también ids que no existen.
 */
public class ConsultarProducto implements Task {

    /** Id del producto a consultar; reemplaza {id} en la ruta. */
    private final String idProducto;

    /** Constructor público usado por Serenity al instrumentar la tarea. */
    public ConsultarProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /** Método de fábrica: ConsultarProducto.conId(1). */
    public static ConsultarProducto conId(Object idProducto) {
        return instrumented(ConsultarProducto.class, String.valueOf(idProducto));
    }

    /** Envía el GET reemplazando el parámetro de ruta {id}. */
    @Override
    @Step("{0} consulta el producto con id #idProducto")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Endpoints.PRODUCTO_POR_ID).with(peticion -> peticion.pathParam("id", idProducto))
        );
    }
}
