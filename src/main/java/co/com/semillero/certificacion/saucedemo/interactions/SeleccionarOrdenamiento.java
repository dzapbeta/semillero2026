package co.com.semillero.certificacion.saucedemo.interactions;

import co.com.semillero.certificacion.saucedemo.models.CriterioDeOrden;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeInventario.LISTA_DE_ORDENAMIENTO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Capa INTERACTIONS: selecciona una opción en la lista desplegable de ordenamiento.
 * POO - POLIMORFISMO: implementa la interfaz Interaction.
 */
public class SeleccionarOrdenamiento implements Interaction {

    /** Criterio de orden elegido (por ejemplo PRECIO_MENOR_A_MAYOR). */
    private final CriterioDeOrden criterio;

    /** Constructor público: guarda el criterio. */
    public SeleccionarOrdenamiento(CriterioDeOrden criterio) {
        this.criterio = criterio;
    }

    /**
     * Busca la lista desplegable para este actor (resolveFor) y selecciona
     * la opción por su atributo value (por ejemplo "lohi").
     */
    @Override
    @Step("{0} ordena los productos por #criterio")
    public <T extends Actor> void performAs(T actor) {
        LISTA_DE_ORDENAMIENTO.resolveFor(actor).selectByValue(criterio.getValor());
    }

    /** Método de fábrica legible: SeleccionarOrdenamiento.por(CriterioDeOrden.NOMBRE_Z_A). */
    public static SeleccionarOrdenamiento por(CriterioDeOrden criterio) {
        return instrumented(SeleccionarOrdenamiento.class, criterio);
    }
}
