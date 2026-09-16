package co.com.semillero.certificacion.saucedemo.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

/**
 * Utilidad para leer valores de configuración del archivo serenity.conf.
 * Así la URL de la aplicación NO queda escrita dentro del código de las tareas.
 */
public final class Configuracion {

    /** Constructor privado: es una clase de utilidades con métodos estáticos. */
    private Configuracion() {
    }

    /**
     * Devuelve la URL base del ambiente activo (bloque "environments" de serenity.conf).
     * Si se ejecuta con -Denvironment=otro, toma la URL de ese ambiente.
     */
    public static String urlBase() {
        return EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty(Constantes.PROPIEDAD_URL_BASE);
    }
}
