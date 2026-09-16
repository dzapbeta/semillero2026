package co.com.semillero.certificacion.dummyjson.tasks;

import co.com.semillero.certificacion.dummyjson.models.Producto;
import co.com.semillero.certificacion.dummyjson.utils.Endpoints;
import co.com.semillero.certificacion.dummyjson.utils.MemoriaDelActor;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * TAREA: crear un producto (POST /products/add) enviando el modelo Producto como JSON.
 * El producto llega desde un DataTable de Cucumber (nivel 3 de data driven).
 */
public class CrearProducto implements Task {

    /** Producto a enviar en el cuerpo de la petición. */
    private final Producto producto;

    /** Constructor público usado por Serenity al instrumentar la tarea. */
    public CrearProducto(Producto producto) {
        this.producto = producto;
    }

    /** Método de fábrica: CrearProducto.conLosDatos(producto). */
    public static CrearProducto conLosDatos(Producto producto) {
        return instrumented(CrearProducto.class, producto);
    }

    /** Guarda en memoria lo que se envió (para compararlo luego) y envía el POST con el JSON del modelo. */
    @Override
    @Step("{0} crea el producto #producto")
    public <T extends Actor> void performAs(T actor) {
        actor.remember(MemoriaDelActor.PRODUCTO_ENVIADO, producto);
        actor.attemptsTo(
                Post.to(Endpoints.CREAR_PRODUCTO).with(peticion -> peticion
                        .contentType(ContentType.JSON)
                        .body(producto))
        );
    }
}
