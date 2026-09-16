package co.com.semillero.certificacion.dummyjson.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

/**
 * Lee la configuración de la API desde src/test/resources/serenity.conf.
 * Así la URL base NO queda escrita en el código: para apuntar a otro ambiente basta con cambiar el archivo
 * o ejecutar con -Drestapi.baseurl=https://otra-url (las propiedades -D tienen prioridad sobre el archivo).
 */
public final class ConfiguracionApi {

    /** Nombre de la propiedad con la URL base en serenity.conf. */
    private static final String PROPIEDAD_URL_BASE = "restapi.baseurl";

    /** Nombre de la propiedad con el tiempo máximo de espera (milisegundos). */
    private static final String PROPIEDAD_TIEMPO_ESPERA = "restapi.timeout.ms";

    /** Tiempo de espera por defecto si la propiedad no existe: 15 segundos. */
    private static final int TIEMPO_ESPERA_POR_DEFECTO = 15000;

    /** Constructor privado: clase de utilidades. */
    private ConfiguracionApi() {
    }

    /** Devuelve la URL base de la API, por ejemplo https://dummyjson.com. */
    public static String urlBase() {
        return EnvironmentSpecificConfiguration.from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty(PROPIEDAD_URL_BASE);
    }

    /** Devuelve el tiempo máximo de espera de cada petición, en milisegundos. */
    public static int tiempoDeEsperaMs() {
        String valor = EnvironmentSpecificConfiguration.from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getOptionalProperty(PROPIEDAD_TIEMPO_ESPERA)
                .orElse(String.valueOf(TIEMPO_ESPERA_POR_DEFECTO));
        return Integer.parseInt(valor);
    }
}
