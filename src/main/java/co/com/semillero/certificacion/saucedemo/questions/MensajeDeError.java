package co.com.semillero.certificacion.saucedemo.questions;

import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeLogin.MENSAJE_DE_ERROR;

/**
 * Capa QUESTIONS: pregunta "¿qué mensaje de error muestra el login?".
 * POO - POLIMORFISMO: implementa la interfaz Question&lt;String&gt;.
 */
@Subject("el mensaje de error del login")
public class MensajeDeError implements Question<String> {

    /** Devuelve el texto del mensaje de error sin espacios sobrantes. */
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(MENSAJE_DE_ERROR).answeredBy(actor).trim();
    }

    /** Método de fábrica legible: MensajeDeError.delLogin(). */
    public static MensajeDeError delLogin() {
        return new MensajeDeError();
    }
}
