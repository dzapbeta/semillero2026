package co.com.semillero.certificacion.saucedemo.exceptions;

/**
 * Excepción que se lanza cuando una validación del inicio de sesión falla.
 * POO - HERENCIA: es un ErrorDeValidacion (y por lo tanto también un AssertionError).
 */
public class ErrorEnInicioDeSesion extends ErrorDeValidacion {

    /** Mensaje cuando el usuario no llega a la página de productos después de iniciar sesión. */
    public static final String NO_INGRESO_A_PRODUCTOS =
            "El usuario no ingresó a la sección de productos después de iniciar sesión";

    /** Mensaje cuando el texto de error mostrado no es el esperado. */
    public static final String MENSAJE_DE_ERROR_DIFERENTE =
            "El mensaje de error del login no es el esperado";

    /** Constructor: pasa mensaje y causa a la clase padre con super(...). */
    public ErrorEnInicioDeSesion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
