package co.com.semillero.certificacion.saucedemo.stepsdefinitions;

import co.com.semillero.certificacion.saucedemo.exceptions.ErrorEnInicioDeSesion;
import co.com.semillero.certificacion.saucedemo.questions.MensajeDeError;
import co.com.semillero.certificacion.saucedemo.questions.TituloDeLaSeccion;
import co.com.semillero.certificacion.saucedemo.tasks.AbrirLaTienda;
import co.com.semillero.certificacion.saucedemo.tasks.IniciarSesion;
import co.com.semillero.certificacion.saucedemo.utils.LectorDeUsuarios;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/**
 * STEP DEFINITIONS del login: conectan cada frase del feature con código Java.
 * Regla: aquí NO hay localizadores ni clics; solo se llama a Tasks y Questions.
 */
public class LoginStepDefinitions {

    /**
     * Frase: Dado que "Carlos" abre la tienda Sauce Demo
     * Crea (o recupera) al actor con ese nombre y le pide abrir la tienda.
     */
    @Dado("que {string} abre la tienda Sauce Demo")
    public void abreLaTiendaSauceDemo(String nombreDelActor) {
        theActorCalled(nombreDelActor).wasAbleTo(AbrirLaTienda.sauceDemo());
    }

    /**
     * Frase: Cuando inicia sesión como usuario "estandar"
     * Lee las credenciales del tipo de usuario y ejecuta la tarea IniciarSesion.
     */
    @Cuando("inicia sesión como usuario {string}")
    public void iniciaSesionComoUsuario(String tipoDeUsuario) {
        theActorInTheSpotlight().attemptsTo(
                IniciarSesion.conCredenciales(LectorDeUsuarios.deTipo(tipoDeUsuario))
        );
    }

    /**
     * Frase: Entonces debería ver la sección "Products"
     * Pregunta el título y, si no coincide, falla con la excepción propia ErrorEnInicioDeSesion.
     */
    @Entonces("debería ver la sección {string}")
    public void deberiaVerLaSeccion(String tituloEsperado) {
        theActorInTheSpotlight().should(
                seeThat(TituloDeLaSeccion.visible(), equalTo(tituloEsperado))
                        .orComplainWith(ErrorEnInicioDeSesion.class,
                                ErrorEnInicioDeSesion.NO_INGRESO_A_PRODUCTOS)
        );
    }

    /**
     * Frase: Entonces debería ver el mensaje de error "Epic sadface: ..."
     * Compara el mensaje real con el esperado.
     */
    @Entonces("debería ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat(MensajeDeError.delLogin(), equalTo(mensajeEsperado))
                        .orComplainWith(ErrorEnInicioDeSesion.class,
                                ErrorEnInicioDeSesion.MENSAJE_DE_ERROR_DIFERENTE)
        );
    }
}
