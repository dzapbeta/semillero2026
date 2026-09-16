package co.com.semillero.certificacion.saucedemo.questions;

import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeCheckout.MENSAJE_DE_CONFIRMACION;

/**
 * Capa QUESTIONS: pregunta "¿qué mensaje muestra la página al terminar la compra?".
 * POO - POLIMORFISMO: implementa la interfaz Question&lt;String&gt;.
 */
@Subject("el mensaje de confirmación de la compra")
public class MensajeDeConfirmacion implements Question<String> {

    /** Devuelve el texto del encabezado de confirmación. */
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(MENSAJE_DE_CONFIRMACION).answeredBy(actor).trim();
    }

    /** Método de fábrica legible: MensajeDeConfirmacion.deLaCompra(). */
    public static MensajeDeConfirmacion deLaCompra() {
        return new MensajeDeConfirmacion();
    }
}
