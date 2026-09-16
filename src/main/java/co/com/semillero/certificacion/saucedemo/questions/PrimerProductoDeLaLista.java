package co.com.semillero.certificacion.saucedemo.questions;

import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeInventario.NOMBRES_DE_PRODUCTOS;

/**
 * Capa QUESTIONS: pregunta "¿cuál es el primer producto que se ve en el catálogo?".
 * POO - POLIMORFISMO: implementa la interfaz Question&lt;String&gt;.
 */
@Subject("el primer producto de la lista")
public class PrimerProductoDeLaLista implements Question<String> {

    /** Obtiene todos los nombres de productos y devuelve el texto del primero. */
    @Override
    public String answeredBy(Actor actor) {
        return NOMBRES_DE_PRODUCTOS.resolveAllFor(actor).get(0).getText().trim();
    }

    /** Método de fábrica legible: PrimerProductoDeLaLista.visible(). */
    public static PrimerProductoDeLaLista visible() {
        return new PrimerProductoDeLaLista();
    }
}
