package co.com.semillero.certificacion.saucedemo.questions;

import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeInventario.CANTIDAD_EN_CARRITO;

/**
 * Capa QUESTIONS: pregunta "¿cuántos productos muestra el ícono del carrito?".
 * POO - POLIMORFISMO: implementa la interfaz Question&lt;Integer&gt;.
 */
@Subject("la cantidad de productos en el carrito")
public class CantidadEnCarrito implements Question<Integer> {

    /**
     * Si el número rojo no existe, el carrito está vacío (0).
     * Si existe, convierte su texto ("1") a número entero.
     */
    @Override
    public Integer answeredBy(Actor actor) {
        List<WebElementFacade> insignias = CANTIDAD_EN_CARRITO.resolveAllFor(actor);
        if (insignias.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(insignias.get(0).getText().trim());
    }

    /** Método de fábrica legible: CantidadEnCarrito.actual(). */
    public static CantidadEnCarrito actual() {
        return new CantidadEnCarrito();
    }
}
