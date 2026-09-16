package co.com.semillero.certificacion.dummyjson.stepsdefinitions;

import co.com.semillero.certificacion.dummyjson.tasks.ConsultarPerfil;
import co.com.semillero.certificacion.dummyjson.tasks.IniciarSesion;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

/**
 * STEPS DE AUTENTICACIÓN (features/autenticacion/inicio_sesion.feature).
 * Cada método traduce una frase de Gherkin en una Task que ejecuta el actor.
 */
public class AutenticacionStepDefinitions {

    /** Precondición: el analista ya inició sesión (se usa en "Dado" antes de consultar el perfil). */
    @Dado("que el analista inició sesión con el usuario {string} y la clave {string}")
    public void queElAnalistaInicioSesion(String usuario, String clave) {
        theActorInTheSpotlight().wasAbleTo(IniciarSesion.con(usuario, clave));
    }

    /** Acción principal: iniciar sesión. Los valores llegan de las columnas usuario y clave de los Ejemplos. */
    @Cuando("el analista inicia sesión con el usuario {string} y la clave {string}")
    public void elAnalistaIniciaSesion(String usuario, String clave) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.con(usuario, clave));
    }

    /** Consulta GET /auth/me enviando el token guardado en la memoria del actor. */
    @Cuando("consulta su perfil con el token de acceso")
    public void consultaSuPerfilConToken() {
        theActorInTheSpotlight().attemptsTo(ConsultarPerfil.conSuToken());
    }

    /** Consulta GET /auth/me sin token (caso negativo). */
    @Cuando("el analista consulta su perfil sin token de acceso")
    public void consultaSuPerfilSinToken() {
        theActorInTheSpotlight().attemptsTo(ConsultarPerfil.sinToken());
    }
}
