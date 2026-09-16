package co.com.semillero.certificacion.saucedemo.tasks;

import co.com.semillero.certificacion.saucedemo.interactions.EsperarVisible;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeCheckout.BOTON_FINALIZAR;
import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeCheckout.MENSAJE_DE_CONFIRMACION;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Capa TASKS: tarea de negocio "finalizar la compra" desde el resumen del pedido.
 * POO - POLIMORFISMO: implementa la interfaz Task.
 */
public class FinalizarCompra implements Task {

    /** Espera el botón "Finish", lo pulsa y espera a que aparezca la confirmación. */
    @Override
    @Step("{0} finaliza la compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EsperarVisible.elElemento(BOTON_FINALIZAR),
                Click.on(BOTON_FINALIZAR),
                EsperarVisible.elElemento(MENSAJE_DE_CONFIRMACION)
        );
    }

    /** Método de fábrica legible: FinalizarCompra.confirmandoLaOrden(). */
    public static FinalizarCompra confirmandoLaOrden() {
        return instrumented(FinalizarCompra.class);
    }
}
