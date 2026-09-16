package co.com.semillero.certificacion.saucedemo.questions;

import co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaProductos;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

// Pregunta: el actor mira la pantalla y responde qué título ve.
public class TituloDeLaPagina implements Question<String> {

    // Devuelve el texto del título que se ve en la página de productos.
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(PaginaProductos.TITULO).answeredBy(actor);
    }

    // Forma cómoda de crear la pregunta: TituloDeLaPagina.visible().
    public static TituloDeLaPagina visible() {
        return new TituloDeLaPagina();
    }
}
