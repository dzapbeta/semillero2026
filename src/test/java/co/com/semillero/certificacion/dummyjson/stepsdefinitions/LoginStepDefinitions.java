package co.com.semillero.certificacion.dummyjson.stepsdefinitions;

import co.com.semillero.certificacion.dummyjson.exceptions.RespuestaInesperada;
import co.com.semillero.certificacion.dummyjson.questions.CampoDeLaRespuesta;
import co.com.semillero.certificacion.dummyjson.questions.CodigoDeRespuesta;
import co.com.semillero.certificacion.dummyjson.tasks.IniciarSesion;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.hamcrest.Matchers.equalTo;

// Traduce las frases de login.feature a acciones del actor.
// Los pasos de validación de código y campo también los usa productos.feature.
public class LoginStepDefinitions {

    // Nombre del actor. Es el que aparece en el reporte.
    private static final String ACTOR = "el analista";

    // "Cuando el analista inicia sesión con el usuario ... y la clave ..."
    @Cuando("el analista inicia sesión con el usuario {string} y la clave {string}")
    public void iniciarSesion(String usuario, String clave) {
        theActorCalled(ACTOR).attemptsTo(IniciarSesion.con(usuario, clave));
    }

    // "Entonces el código de respuesta debe ser ..." Compara el código real con el esperado.
    @Entonces("el código de respuesta debe ser {int}")
    public void validarCodigo(int codigoEsperado) {
        theActorCalled(ACTOR).should(
                seeThat(CodigoDeRespuesta.obtenido(), equalTo(codigoEsperado))
                        .orComplainWith(RespuestaInesperada.class, RespuestaInesperada.CODIGO_DIFERENTE)
        );
    }

    // "Y el campo ... de la respuesta debe ser ..." Compara un campo del JSON con el valor esperado.
    @Entonces("el campo {string} de la respuesta debe ser {string}")
    public void validarCampo(String campo, String valorEsperado) {
        theActorCalled(ACTOR).should(
                seeThat(CampoDeLaRespuesta.llamado(campo), equalTo(valorEsperado))
                        .orComplainWith(RespuestaInesperada.class, RespuestaInesperada.CAMPO_DIFERENTE)
        );
    }
}
