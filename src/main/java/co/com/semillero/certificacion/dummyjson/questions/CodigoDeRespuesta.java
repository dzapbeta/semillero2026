package co.com.semillero.certificacion.dummyjson.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * PREGUNTA: ¿qué código HTTP devolvió la última petición? (200, 201, 400, 401, 404...).
 * Las preguntas NO hacen acciones: solo consultan el estado (aquí, la última respuesta) y devuelven un valor.
 *
 * POO - POLIMORFISMO: implementa Question<Integer>; seeThat(...) puede recibir esta o cualquier otra Question.
 */
public class CodigoDeRespuesta implements Question<Integer> {

    /** Método de fábrica legible: seeThat(CodigoDeRespuesta.obtenido(), equalTo(200)). */
    public static CodigoDeRespuesta obtenido() {
        return new CodigoDeRespuesta();
    }

    /** Devuelve el código de estado de la última respuesta que recibió el actor. */
    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }

    /** Texto que aparece en el reporte: "el código de respuesta HTTP". */
    @Override
    public String toString() {
        return "el código de respuesta HTTP";
    }
}
