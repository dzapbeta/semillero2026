package co.com.semillero.certificacion.saucedemo.exceptions;

/**
 * Excepción que se lanza cuando una validación del flujo de compra falla.
 * POO - HERENCIA: hereda de ErrorDeValidacion.
 */
public class ErrorEnLaCompra extends ErrorDeValidacion {

    /** Mensaje cuando el ícono del carrito no muestra la cantidad esperada. */
    public static final String CANTIDAD_EN_CARRITO_INCORRECTA =
            "La cantidad de productos en el carrito no es la esperada";

    /** Mensaje cuando no aparece la confirmación de la orden. */
    public static final String COMPRA_NO_CONFIRMADA =
            "La compra no mostró el mensaje de confirmación esperado";

    /** Constructor: pasa mensaje y causa a la clase padre. */
    public ErrorEnLaCompra(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
