package co.com.semillero.certificacion.saucedemo.questions;

import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeInventario.TITULO_DE_LA_SECCION;

/**
 * Capa QUESTIONS: pregunta "¿qué título muestra la sección actual?".
 * Una Question solo CONSULTA el estado de la pantalla; nunca hace clic ni escribe.
 * POO - POLIMORFISMO: implementa la interfaz Question&lt;String&gt;.
 */
@Subject("el título de la sección")
public class TituloDeLaSeccion implements Question<String> {

    /** Lee el texto visible del título usando la pregunta Text que trae Serenity. */
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(TITULO_DE_LA_SECCION).answeredBy(actor).trim();
    }

    /** Método de fábrica legible: TituloDeLaSeccion.visible(). */
    public static TituloDeLaSeccion visible() {
        return new TituloDeLaSeccion();
    }
}
