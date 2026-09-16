package co.com.semillero.certificacion.dummyjson.exceptions;

/**
 * Se lanza cuando un dato de prueba no existe o el archivo de datos no se puede leer
 * (por ejemplo, un caso "P09" que no está en productos.csv o un nombre de archivo mal escrito).
 * POO - HERENCIA: hereda de ErrorDeAutomatizacion. Así el error dice claramente que el problema está en los DATOS
 * y no en la API.
 */
public class DatoDePruebaNoEncontrado extends ErrorDeAutomatizacion {

    /** Constructor con el mensaje que explica qué dato faltó. */
    public DatoDePruebaNoEncontrado(String mensaje) {
        super(mensaje);
    }

    /** Constructor con mensaje y causa (por ejemplo, el error original de lectura del archivo). */
    public DatoDePruebaNoEncontrado(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
