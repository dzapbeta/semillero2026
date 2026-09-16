package co.com.semillero.certificacion.dummyjson.exceptions;

/**
 * EXCEPCIÓN BASE del proyecto.
 * POO - HERENCIA: extiende AssertionError, que es el tipo de error que JUnit/Serenity marcan como "prueba fallida".
 * Todas nuestras excepciones heredan de esta clase, así comparten comportamiento y se reconocen como propias.
 */
public class ErrorDeAutomatizacion extends AssertionError {

    /**
     * Constructor con mensaje y causa. Serenity lo necesita EXACTAMENTE con esta firma (String, Throwable)
     * para poder crear la excepción cuando se usa .orComplainWith(...).
     */
    public ErrorDeAutomatizacion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /** Constructor solo con mensaje, para lanzarla directamente con throw. */
    public ErrorDeAutomatizacion(String mensaje) {
        super(mensaje);
    }
}
