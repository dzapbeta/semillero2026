package co.com.semillero.certificacion.dummyjson.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

// Pregunta: ¿qué código HTTP devolvió la última petición? (200, 400, 404...)
// Las preguntas no hacen nada en la API, solo miran la respuesta y devuelven un dato.
public class CodigoDeRespuesta implements Question<Integer> {

    // Forma cómoda de crear la pregunta: CodigoDeRespuesta.obtenido()
    public static CodigoDeRespuesta obtenido() {
        return new CodigoDeRespuesta();
    }

    // Devuelve el código de la última respuesta que recibió el actor.
    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }
}
