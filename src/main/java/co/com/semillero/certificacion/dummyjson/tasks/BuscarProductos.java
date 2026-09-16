package co.com.semillero.certificacion.dummyjson.tasks;

import co.com.semillero.certificacion.dummyjson.utils.Endpoints;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * TAREA: buscar productos por texto (GET /products/search?q=texto).
 */
public class BuscarProductos implements Task {

    /** Texto a buscar; viaja como parámetro de consulta "q". */
    private final String texto;

    /** Constructor público usado por Serenity al instrumentar la tarea. */
    public BuscarProductos(String texto) {
        this.texto = texto;
    }

    /** Método de fábrica: BuscarProductos.conElTexto("iphone"). */
    public static BuscarProductos conElTexto(String texto) {
        return instrumented(BuscarProductos.class, texto);
    }

    /** Envía el GET con el parámetro de consulta q. */
    @Override
    @Step("{0} busca productos con el texto '#texto'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Endpoints.BUSCAR_PRODUCTOS).with(peticion -> peticion.queryParam("q", texto))
        );
    }
}
