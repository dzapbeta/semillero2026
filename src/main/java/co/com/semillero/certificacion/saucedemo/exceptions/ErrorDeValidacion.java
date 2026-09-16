package co.com.semillero.certificacion.saucedemo.exceptions;

/**
 * Excepción BASE del proyecto para validaciones que no se cumplen.
 * POO - HERENCIA: extiende AssertionError, el tipo de error que usan las pruebas
 * cuando un resultado esperado no coincide con el real.
 * Las demás excepciones del proyecto heredan de esta clase.
 */
public class ErrorDeValidacion extends AssertionError {

    /**
     * Constructor que exige Serenity para usar .orComplainWith(...):
     * recibe el mensaje propio y la causa original (el error de la comparación).
     */
    public ErrorDeValidacion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
