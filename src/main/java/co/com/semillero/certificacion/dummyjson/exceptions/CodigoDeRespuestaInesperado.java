package co.com.semillero.certificacion.dummyjson.exceptions;

/**
 * Se lanza cuando el código HTTP de la respuesta (200, 201, 400, 404...) no es el esperado.
 * POO - HERENCIA: hereda de ErrorDeAutomatizacion (y por lo tanto de AssertionError).
 * Uso: seeThat(CodigoDeRespuesta.obtenido(), equalTo(200)).orComplainWith(CodigoDeRespuestaInesperado.class, MENSAJE)
 */
public class CodigoDeRespuestaInesperado extends ErrorDeAutomatizacion {

    /** Mensaje que se muestra en el reporte cuando falla la validación del código de respuesta. */
    public static final String MENSAJE = "La API respondió con un código HTTP diferente al esperado";

    /** Constructor que usa Serenity al ejecutar orComplainWith (mensaje + causa original). */
    public CodigoDeRespuestaInesperado(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
