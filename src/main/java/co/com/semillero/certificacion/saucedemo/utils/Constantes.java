package co.com.semillero.certificacion.saucedemo.utils;

// Guarda los datos fijos del proyecto en un solo lugar. Si la URL cambia, solo se cambia aquí.
public class Constantes {

    // Dirección de la tienda que vamos a probar.
    public static final String URL_SAUCE_DEMO = "https://www.saucedemo.com";

    // Mensaje que sale en el reporte cuando el título de la página no es el esperado.
    public static final String TITULO_DIFERENTE = "El título de la página no es el que esperaba el escenario";

    // Mensaje que sale en el reporte cuando el mensaje de error no es el esperado.
    public static final String MENSAJE_DE_ERROR_DIFERENTE = "El mensaje de error no es el que esperaba el escenario";

    // Constructor privado: esta clase solo guarda constantes, no se crean objetos de ella.
    private Constantes() {
    }
}
