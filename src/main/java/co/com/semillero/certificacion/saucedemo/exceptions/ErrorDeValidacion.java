package co.com.semillero.certificacion.saucedemo.exceptions;

// Error propio del proyecto. Se lanza cuando lo que ve el actor no es lo que esperaba el escenario.
// Extiende AssertionError para que la prueba se marque como fallida (no como rota).
public class ErrorDeValidacion extends AssertionError {

    // Constructor que usa Serenity con orComplainWith: recibe nuestro mensaje y el error original.
    public ErrorDeValidacion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
