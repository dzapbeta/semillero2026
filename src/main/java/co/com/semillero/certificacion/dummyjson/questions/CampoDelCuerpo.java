package co.com.semillero.certificacion.dummyjson.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * PREGUNTA: ¿qué valor tiene un campo del cuerpo JSON de la última respuesta?
 * Usa JSON path: "message", "username", "total", "products[0].title", etc.
 * Devuelve el valor como texto (o null si el campo no existe).
 */
public class CampoDelCuerpo implements Question<String> {

    /** Ruta JSON del campo a leer. */
    private final String rutaJson;

    /** Constructor privado: se crea con el método de fábrica {@link #enLaRuta(String)}. */
    private CampoDelCuerpo(String rutaJson) {
        this.rutaJson = rutaJson;
    }

    /** Método de fábrica: CampoDelCuerpo.enLaRuta("message"). */
    public static CampoDelCuerpo enLaRuta(String rutaJson) {
        return new CampoDelCuerpo(rutaJson);
    }

    /** Lee el campo con JSON path y lo devuelve como String. */
    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getString(rutaJson);
    }

    /** Texto que aparece en el reporte: "el campo 'message' de la respuesta". */
    @Override
    public String toString() {
        return "el campo '" + rutaJson + "' de la respuesta";
    }
}
