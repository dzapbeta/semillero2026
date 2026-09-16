package co.com.semillero.certificacion.dummyjson.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * PREGUNTA GENÉRICA: convierte TODO el cuerpo JSON de la última respuesta en un modelo Java.
 * <T> significa "cualquier tipo": sirve para Producto hoy y para Usuario o Carrito mañana, sin escribir otra clase.
 */
public class CuerpoDeLaRespuesta<T> implements Question<T> {

    /** Clase del modelo al que se convierte el JSON (por ejemplo Producto.class). */
    private final Class<T> tipoDeModelo;

    /** Constructor privado: se crea con {@link #comoModelo(Class)}. */
    private CuerpoDeLaRespuesta(Class<T> tipoDeModelo) {
        this.tipoDeModelo = tipoDeModelo;
    }

    /** Método de fábrica: CuerpoDeLaRespuesta.comoModelo(Producto.class). */
    public static <T> CuerpoDeLaRespuesta<T> comoModelo(Class<T> tipoDeModelo) {
        return new CuerpoDeLaRespuesta<>(tipoDeModelo);
    }

    /** Convierte (deserializa) el JSON de la respuesta en un objeto del tipo pedido usando Jackson. */
    @Override
    public T answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(tipoDeModelo);
    }

    /** Texto para el reporte: "el cuerpo de la respuesta como Producto". */
    @Override
    public String toString() {
        return "el cuerpo de la respuesta como " + tipoDeModelo.getSimpleName();
    }
}
