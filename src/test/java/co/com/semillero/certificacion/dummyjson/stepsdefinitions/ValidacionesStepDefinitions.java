package co.com.semillero.certificacion.dummyjson.stepsdefinitions;

import co.com.semillero.certificacion.dummyjson.exceptions.CodigoDeRespuestaInesperado;
import co.com.semillero.certificacion.dummyjson.exceptions.CuerpoDeRespuestaInesperado;
import co.com.semillero.certificacion.dummyjson.questions.CampoDelCuerpo;
import co.com.semillero.certificacion.dummyjson.questions.CodigoDeRespuesta;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * STEPS DE VALIDACIÓN COMUNES: los usan todos los features (código de respuesta y campos del JSON).
 * Un step NO tiene lógica: solo conecta la frase del feature con una Question y un comparador de Hamcrest.
 */
public class ValidacionesStepDefinitions {

    /**
     * Valida el código HTTP. {int} captura el número del feature (200, 201, 400...).
     * orComplainWith: si falla, lanza nuestra excepción propia con un mensaje claro en el reporte.
     */
    @Entonces("el código de respuesta debe ser {int}")
    public void validarCodigoDeRespuesta(int codigoEsperado) {
        theActorInTheSpotlight().should(
                seeThat(CodigoDeRespuesta.obtenido(), equalTo(codigoEsperado))
                        .orComplainWith(CodigoDeRespuestaInesperado.class, CodigoDeRespuestaInesperado.MENSAJE)
        );
    }

    /** Valida el valor de un campo del JSON. El primer {string} es la ruta JSON; el segundo, el valor esperado. */
    @Y("el campo {string} de la respuesta debe ser {string}")
    public void validarCampoDeLaRespuesta(String rutaJson, String valorEsperado) {
        theActorInTheSpotlight().should(
                seeThat(CampoDelCuerpo.enLaRuta(rutaJson), equalTo(valorEsperado))
                        .orComplainWith(CuerpoDeRespuestaInesperado.class, CuerpoDeRespuestaInesperado.MENSAJE_CAMPO)
        );
    }

    /** Valida que la respuesta del login traiga un accessToken no vacío. */
    @Y("la respuesta debe traer un token de acceso")
    public void validarTokenDeAcceso() {
        theActorInTheSpotlight().should(
                seeThat(CampoDelCuerpo.enLaRuta("accessToken"), not(emptyOrNullString()))
                        .orComplainWith(CuerpoDeRespuestaInesperado.class, CuerpoDeRespuestaInesperado.MENSAJE_CAMPO)
        );
    }
}
