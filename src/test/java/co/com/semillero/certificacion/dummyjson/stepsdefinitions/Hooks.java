package co.com.semillero.certificacion.dummyjson.stepsdefinitions;

import co.com.semillero.certificacion.dummyjson.utils.ConfiguracionApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

/**
 * HOOKS: código que Cucumber ejecuta automáticamente ANTES y DESPUÉS de cada escenario.
 * Aquí se prepara el "escenario de teatro" de Screenplay y se crea el actor con su habilidad de consumir la API.
 */
public class Hooks {

    /** Nombre del actor que aparece en el reporte. En los features se le llama "el analista". */
    public static final String NOMBRE_DEL_ACTOR = "Analista QA";

    /**
     * Antes de cada escenario:
     * 1. setTheStage(new OnlineCast()): prepara el escenario donde actúan los actores (el mismo de la rama Web;
     *    por eso en el reporte el actor también dice "browse the web", aunque aquí no se abre navegador).
     * 2. Configura el tiempo máximo de espera de las peticiones (leído de serenity.conf) para no quedarse colgado.
     * 3. Crea el actor y le da la HABILIDAD CallAnApi con la URL base leída de serenity.conf.
     *    En Web la habilidad era BrowseTheWeb (navegador); en API es CallAnApi (cliente HTTP).
     */
    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
        int tiempoDeEspera = ConfiguracionApi.tiempoDeEsperaMs();
        SerenityRest.setDefaultConfig(RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", tiempoDeEspera)
                .setParam("http.socket.timeout", tiempoDeEspera)));
        OnStage.theActorCalled(NOMBRE_DEL_ACTOR).whoCan(CallAnApi.at(ConfiguracionApi.urlBase()));
    }

    /** Después de cada escenario: "baja el telón" y libera los actores para que el siguiente escenario empiece limpio. */
    @After
    public void cerrarEscenario() {
        OnStage.drawTheCurtain();
    }
}
