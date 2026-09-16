package co.com.semillero.playwright.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

/**
 * Lee la configuración de las pruebas desde {@code config.properties}.
 * <p>
 * Orden de prioridad (gana el primero que exista):
 * <ol>
 *   <li>Propiedad de la línea de comandos: {@code mvn test -Dheadless=false}</li>
 *   <li>Valor del archivo {@code src/test/resources/config.properties}</li>
 * </ol>
 * Así el mismo código corre en CI (headless) y en clase (navegador visible) sin tocar Java.
 * <b>Encapsulamiento:</b> el objeto {@link Properties} es privado; solo se exponen métodos con tipo.
 */
public final class Configuracion {

    /** Nombre del archivo de configuración que se busca en el classpath (src/test/resources). */
    private static final String ARCHIVO = "config.properties";

    /** Valores leídos del archivo. Se carga una sola vez al usar la clase (bloque static). */
    private static final Properties PROPIEDADES = cargar();

    /** Constructor privado: esta clase solo tiene métodos estáticos y no se debe instanciar. */
    private Configuracion() {
    }

    /**
     * Carga el archivo {@code config.properties} desde el classpath.
     *
     * @return las propiedades leídas
     */
    private static Properties cargar() {
        Properties propiedades = new Properties();
        try (InputStream entrada = Configuracion.class.getClassLoader().getResourceAsStream(ARCHIVO)) {
            if (entrada == null) {
                throw new IllegalStateException("No se encontró " + ARCHIVO + " en src/test/resources");
            }
            propiedades.load(entrada);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer " + ARCHIVO, e);
        }
        return propiedades;
    }

    /**
     * Devuelve el valor de una clave: primero mira {@code -Dclave=valor} y si no, el archivo.
     *
     * @param clave nombre de la propiedad (ej. "navegador")
     * @return el valor encontrado, sin espacios alrededor
     */
    private static String valor(String clave) {
        String valor = System.getProperty(clave, PROPIEDADES.getProperty(clave));
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException("Falta la propiedad '" + clave + "' en " + ARCHIVO);
        }
        return valor.trim();
    }

    /** @return URL base de la aplicación bajo prueba (ej. https://www.saucedemo.com) */
    public static String baseUrl() {
        return valor("baseUrl");
    }

    /** @return true si el navegador corre sin ventana (headless); false para verlo en pantalla */
    public static boolean headless() {
        return Boolean.parseBoolean(valor("headless"));
    }

    /** @return nombre del navegador: chromium, firefox o webkit */
    public static String navegador() {
        return valor("navegador").toLowerCase();
    }

    /** @return milisegundos de pausa entre cada acción (0 = velocidad normal; 500 para verlo en clase) */
    public static double slowMo() {
        return Double.parseDouble(valor("slowMo"));
    }

    /** @return tiempo máximo (ms) que esperan las acciones y aserciones antes de fallar */
    public static double timeoutMs() {
        return Double.parseDouble(valor("timeoutMs"));
    }

    /** @return "fallos" para guardar la traza solo si la prueba falla, o "siempre" para guardarla siempre */
    public static String guardarTraza() {
        return valor("guardarTraza").toLowerCase();
    }
}
