package co.com.semillero.certificacion.saucedemo.tasks;

import co.com.semillero.certificacion.saucedemo.interactions.AbrirPagina;
import co.com.semillero.certificacion.saucedemo.interactions.EsperarVisible;
import co.com.semillero.certificacion.saucedemo.utils.Configuracion;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeLogin.CAMPO_USUARIO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Capa TASKS: tarea de negocio "abrir la tienda Sauce Demo".
 * Una Task agrupa interacciones para cumplir un objetivo que entiende el negocio.
 * POO - POLIMORFISMO: implementa la interfaz Task.
 */
public class AbrirLaTienda implements Task {

    /**
     * Pasos de la tarea: abrir la URL configurada en serenity.conf y esperar
     * a que aparezca el formulario de login.
     */
    @Override
    @Step("{0} abre la tienda Sauce Demo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                AbrirPagina.enLaUrl(Configuracion.urlBase()),
                EsperarVisible.elElemento(CAMPO_USUARIO)
        );
    }

    /** Método de fábrica legible: AbrirLaTienda.sauceDemo(). */
    public static AbrirLaTienda sauceDemo() {
        return instrumented(AbrirLaTienda.class);
    }
}
