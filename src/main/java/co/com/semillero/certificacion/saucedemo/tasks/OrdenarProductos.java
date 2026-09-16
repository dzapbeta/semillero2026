package co.com.semillero.certificacion.saucedemo.tasks;

import co.com.semillero.certificacion.saucedemo.interactions.SeleccionarOrdenamiento;
import co.com.semillero.certificacion.saucedemo.models.CriterioDeOrden;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Capa TASKS: tarea de negocio "ordenar el catálogo de productos".
 * Delega el detalle técnico en la interacción propia SeleccionarOrdenamiento.
 * POO - POLIMORFISMO: implementa la interfaz Task.
 */
public class OrdenarProductos implements Task {

    /** Criterio de orden solicitado desde el feature. */
    private final CriterioDeOrden criterio;

    /** Constructor público: guarda el criterio. */
    public OrdenarProductos(CriterioDeOrden criterio) {
        this.criterio = criterio;
    }

    /** Ejecuta la interacción que selecciona la opción en la lista desplegable. */
    @Override
    @Step("{0} ordena el catálogo por #criterio")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SeleccionarOrdenamiento.por(criterio)
        );
    }

    /** Método de fábrica legible: OrdenarProductos.por(CriterioDeOrden.PRECIO_MENOR_A_MAYOR). */
    public static OrdenarProductos por(CriterioDeOrden criterio) {
        return instrumented(OrdenarProductos.class, criterio);
    }
}
