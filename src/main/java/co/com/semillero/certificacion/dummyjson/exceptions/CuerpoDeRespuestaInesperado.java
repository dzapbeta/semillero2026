package co.com.semillero.certificacion.dummyjson.exceptions;

/**
 * Se lanza cuando el CUERPO (JSON) de la respuesta no contiene lo esperado: un campo con otro valor o un modelo distinto.
 * POO - HERENCIA: hereda de ErrorDeAutomatizacion.
 */
public class CuerpoDeRespuestaInesperado extends ErrorDeAutomatizacion {

    /** Mensaje para el reporte cuando un campo del JSON no coincide. */
    public static final String MENSAJE_CAMPO = "Un campo del cuerpo de la respuesta no tiene el valor esperado";

    /** Mensaje para el reporte cuando el objeto completo (modelo) no coincide. */
    public static final String MENSAJE_MODELO = "El cuerpo de la respuesta no coincide con los datos enviados o esperados";

    /** Constructor que usa Serenity al ejecutar orComplainWith (mensaje + causa original). */
    public CuerpoDeRespuestaInesperado(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
