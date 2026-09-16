package co.com.semillero.certificacion.dummyjson.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

// Pregunta: ¿qué valor tiene un campo del JSON de la última respuesta?
// Ejemplo: en {"message": "Invalid credentials"} el campo "message" vale "Invalid credentials".
public class CampoDeLaRespuesta implements Question<String> {

    // Nombre del campo que queremos leer, por ejemplo "title" o "message".
    private final String campo;

    // Guarda el nombre del campo. Se usa desde el método llamado(...).
    public CampoDeLaRespuesta(String campo) {
        this.campo = campo;
    }

    // Forma cómoda de crear la pregunta: CampoDeLaRespuesta.llamado("title")
    public static CampoDeLaRespuesta llamado(String campo) {
        return new CampoDeLaRespuesta(campo);
    }

    // Busca el campo en el JSON de la última respuesta y lo devuelve como texto.
    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getString(campo);
    }
}
