package co.com.semillero.certificacion.saucedemo.stepsdefinitions;

import co.com.semillero.certificacion.saucedemo.exceptions.ErrorEnElOrdenamiento;
import co.com.semillero.certificacion.saucedemo.models.CriterioDeOrden;
import co.com.semillero.certificacion.saucedemo.questions.PrimerProductoDeLaLista;
import co.com.semillero.certificacion.saucedemo.tasks.OrdenarProductos;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/**
 * STEP DEFINITIONS del ordenamiento del catálogo.
 */
public class OrdenamientoStepDefinitions {

    /**
     * Frase: Cuando ordena los productos por "precio de menor a mayor"
     * Convierte el texto del feature al enum CriterioDeOrden y ejecuta la tarea.
     */
    @Cuando("ordena los productos por {string}")
    public void ordenaLosProductosPor(String descripcionDelCriterio) {
        theActorInTheSpotlight().attemptsTo(
                OrdenarProductos.por(CriterioDeOrden.desdeDescripcion(descripcionDelCriterio))
        );
    }

    /** Frase: Entonces el primer producto de la lista debería ser "Sauce Labs Onesie" */
    @Entonces("el primer producto de la lista debería ser {string}")
    public void elPrimerProductoDeberiaSer(String productoEsperado) {
        theActorInTheSpotlight().should(
                seeThat(PrimerProductoDeLaLista.visible(), equalTo(productoEsperado))
                        .orComplainWith(ErrorEnElOrdenamiento.class,
                                ErrorEnElOrdenamiento.PRIMER_PRODUCTO_INCORRECTO)
        );
    }
}
