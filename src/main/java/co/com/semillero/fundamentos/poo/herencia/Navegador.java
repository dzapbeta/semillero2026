package co.com.semillero.fundamentos.poo.herencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tema 3 · Herencia. Clase PADRE (superclase) de todos los navegadores.
 *
 * <p>Es {@code abstract}: representa la idea general de "un navegador", por eso no se puede hacer
 * {@code new Navegador(...)}. Solo se crean hijos concretos: {@link Chrome}, {@link Firefox}, {@link Edge}.</p>
 *
 * <p>Todo lo común (abrir una URL, llevar historial, validar la dirección) se escribe UNA sola vez aquí
 * y los hijos lo heredan con {@code extends}. Es la misma idea de las clases base de un framework
 * de automatización.</p>
 */
public abstract class Navegador {

    /** Nombre comercial del navegador. Privado: los hijos lo leen con {@link #getNombre()}. */
    private final String nombre;

    /**
     * Si el navegador corre sin ventana visible (modo "headless", típico en servidores de CI).
     * {@code protected}: visible para las clases hijas (y el mismo paquete), pero no para el resto.
     */
    protected final boolean sinVentana;

    /** Direcciones abiertas en orden. Privado: se consulta con {@link #getHistorial()}. */
    private final List<String> historial;

    /**
     * Constructor de la clase padre. Los hijos lo llaman con {@code super(nombre, sinVentana)}
     * como PRIMERA instrucción de su propio constructor.
     *
     * @param nombre     nombre del navegador
     * @param sinVentana {@code true} para modo headless
     */
    protected Navegador(String nombre, boolean sinVentana) {
        this.nombre = nombre;
        this.sinVentana = sinVentana;
        this.historial = new ArrayList<>();
    }

    /**
     * Abre una URL: valida la dirección, la guarda en el historial y devuelve un mensaje.
     * Los hijos pueden sobrescribir este método y reutilizarlo con {@code super.abrir(url)}.
     *
     * @param url dirección que debe empezar por http:// o https://
     * @return mensaje del tipo "Chrome abrió https://..."
     * @throws IllegalArgumentException si la URL no es válida
     */
    public String abrir(String url) {
        validarUrl(url);
        historial.add(url);
        return nombre + " abrió " + url + (sinVentana ? " (sin ventana)" : "");
    }

    /**
     * Método <b>abstracto</b>: no tiene cuerpo. Obliga a cada hijo a decir qué "driver" usa
     * (el programa que Selenium necesita para controlar ese navegador).
     *
     * @return nombre del driver, por ejemplo "chromedriver"
     */
    public abstract String nombreDelDriver();

    /**
     * Describe la configuración del navegador. Los hijos lo sobrescriben para agregar detalles.
     *
     * @return texto con nombre, driver y modo
     */
    public String describir() {
        return nombre + " | driver=" + nombreDelDriver() + " | modo=" + (sinVentana ? "headless" : "con ventana");
    }

    /**
     * Valida que la URL tenga un protocolo web. Es {@code protected}: los hijos la pueden usar,
     * pero no hace parte de lo que ven las pruebas desde afuera.
     *
     * @param url dirección a validar
     * @throws IllegalArgumentException si es nula o no empieza por http:// o https://
     */
    protected void validarUrl(String url) {
        if (url == null || !(url.startsWith("http://") || url.startsWith("https://"))) {
            throw new IllegalArgumentException("URL inválida (debe empezar por http:// o https://): " + url);
        }
    }

    /**
     * Devuelve el nombre del navegador.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Indica si el navegador corre sin ventana.
     *
     * @return {@code true} en modo headless
     */
    public boolean isSinVentana() {
        return sinVentana;
    }

    /**
     * Devuelve las URLs abiertas, en una lista de solo lectura (encapsulamiento).
     *
     * @return historial no modificable
     */
    public List<String> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
}
