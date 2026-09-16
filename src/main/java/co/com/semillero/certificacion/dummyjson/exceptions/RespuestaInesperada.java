package co.com.semillero.certificacion.dummyjson.exceptions;

// Error propio que se muestra cuando la API no responde lo que esperábamos.
// Con orComplainWith(...) Serenity lanza este error con un mensaje en español fácil de entender.
public class RespuestaInesperada extends AssertionError {

    // Mensaje cuando el código HTTP no es el esperado.
    public static final String CODIGO_DIFERENTE = "La API respondió con un código HTTP diferente al esperado";

    // Mensaje cuando un campo del JSON no tiene el valor esperado.
    public static final String CAMPO_DIFERENTE = "Un campo de la respuesta no tiene el valor esperado";

    // Serenity llama este constructor con el mensaje y el error original de la comparación.
    public RespuestaInesperada(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
