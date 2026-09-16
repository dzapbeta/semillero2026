package co.com.semillero.certificacion.saucedemo.questions;

import co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaLogin;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

// Pregunta: el actor lee el mensaje de error que muestra la página de login.
public class MensajeDeError implements Question<String> {

    // Devuelve el texto del mensaje de error.
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(PaginaLogin.MENSAJE_ERROR).answeredBy(actor);
    }

    // Forma cómoda de crear la pregunta: MensajeDeError.visible().
    public static MensajeDeError visible() {
        return new MensajeDeError();
    }
}
