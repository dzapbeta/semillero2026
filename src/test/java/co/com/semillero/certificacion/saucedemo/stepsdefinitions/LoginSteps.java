package co.com.semillero.certificacion.saucedemo.stepsdefinitions;

import co.com.semillero.certificacion.saucedemo.exceptions.ErrorDeValidacion;
import co.com.semillero.certificacion.saucedemo.interactions.AbrirLaPagina;
import co.com.semillero.certificacion.saucedemo.questions.MensajeDeError;
import co.com.semillero.certificacion.saucedemo.questions.TituloDeLaPagina;
import co.com.semillero.certificacion.saucedemo.tasks.IniciarSesion;
import co.com.semillero.certificacion.saucedemo.utils.Constantes;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

// Steps del login: cada método conecta una frase del .feature con el actor.
// Aquí no hay localizadores ni clics; solo se piden tareas y se hacen preguntas.
public class LoginSteps {

    // Frase: Dado que "Ana" abre la página de Sauce Demo
    // Crea el actor con ese nombre y le pide abrir la página.
    @Dado("que {string} abre la página de Sauce Demo")
    public void abreLaPagina(String nombreDelActor) {
        theActorCalled(nombreDelActor).wasAbleTo(AbrirLaPagina.deSauceDemo());
    }

    // Frase: Cuando inicia sesión con el usuario "..." y la clave "..."
    // El actor que está en escena hace la tarea de iniciar sesión.
    @Cuando("inicia sesión con el usuario {string} y la clave {string}")
    public void iniciaSesion(String usuario, String clave) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.con(usuario, clave));
    }

    // Frase: Entonces debería ver el título "Products"
    // Pregunta el título y, si no coincide, falla con nuestro ErrorDeValidacion.
    @Entonces("debería ver el título {string}")
    public void deberiaVerElTitulo(String tituloEsperado) {
        theActorInTheSpotlight().should(
                seeThat(TituloDeLaPagina.visible(), equalTo(tituloEsperado))
                        .orComplainWith(ErrorDeValidacion.class, Constantes.TITULO_DIFERENTE)
        );
    }

    // Frase: Entonces debería ver el mensaje de error "..."
    // Pregunta el mensaje de error y lo compara con el esperado.
    @Entonces("debería ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat(MensajeDeError.visible(), equalTo(mensajeEsperado))
                        .orComplainWith(ErrorDeValidacion.class, Constantes.MENSAJE_DE_ERROR_DIFERENTE)
        );
    }
}
