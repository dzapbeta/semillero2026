package co.com.semillero.certificacion.saucedemo.exceptions;

/**
 * Excepción que se lanza cuando el orden de los productos no es el esperado.
 * POO - HERENCIA: hereda de ErrorDeValidacion.
 */
public class ErrorEnElOrdenamiento extends ErrorDeValidacion {

    /** Mensaje cuando el primer producto de la lista no es el que debería quedar primero. */
    public static final String PRIMER_PRODUCTO_INCORRECTO =
            "Después de ordenar, el primer producto de la lista no es el esperado";

    /** Constructor: pasa mensaje y causa a la clase padre. */
    public ErrorEnElOrdenamiento(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
