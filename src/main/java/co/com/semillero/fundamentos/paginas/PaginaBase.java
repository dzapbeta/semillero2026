package co.com.semillero.fundamentos.paginas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Puente hacia la automatización: clase base de las páginas (patrón Page Object Model, POM).
 *
 * <p>En un proyecto real con Selenium o Playwright, esta clase tendría el "driver" y los métodos
 * genéricos para escribir, hacer clic y leer textos. Aquí la página es <b>simulada</b> con un
 * {@code Map} (localizador → valor) para que se entienda la idea sin abrir un navegador.</p>
 *
 * <ul>
 *   <li><b>Herencia</b>: cada página concreta ({@link PaginaLogin}) extiende esta clase y reutiliza sus acciones.</li>
 *   <li><b>Encapsulamiento</b>: los localizadores y el "DOM" son privados; la prueba solo ve métodos de negocio
 *       como {@code iniciarSesion(...)}.</li>
 *   <li>En <b>Screenplay</b> (clase 2) la misma idea se reparte en Tareas, Interacciones y Preguntas,
 *       pero sigue apoyándose en clases, interfaces y herencia.</li>
 * </ul>
 */
public abstract class PaginaBase {

    /** "DOM" simulado: cada localizador (ej. "#usuario") guarda el valor que contiene. */
    private final Map<String, String> elementos = new HashMap<>();

    /** Registro de las acciones hechas sobre la página, en orden (útil como evidencia). */
    private final List<String> acciones = new ArrayList<>();

    /** Indica si la página ya fue abierta; no se puede interactuar con una página sin abrir. */
    private boolean abierta;

    /**
     * Constructor {@code protected}: solo las páginas hijas lo llaman (con {@code super()}, implícito).
     * La página empieza cerrada.
     */
    protected PaginaBase() {
        this.abierta = false;
    }

    /**
     * Cada página debe decir su dirección. Método abstracto: lo implementa la clase hija.
     *
     * @return URL de la página
     */
    public abstract String getUrl();

    /**
     * Abre la página (simulado) y la deja lista para interactuar.
     *
     * @return mensaje "Abriendo &lt;url&gt;"
     */
    public String abrir() {
        abierta = true;
        acciones.add("abrir " + getUrl());
        return "Abriendo " + getUrl();
    }

    /**
     * Escribe un texto en un campo. {@code protected}: solo las páginas hijas lo usan;
     * la prueba no manipula localizadores directamente.
     *
     * @param localizador identificador del campo, por ejemplo "#usuario"
     * @param texto       texto a escribir
     */
    protected void escribir(String localizador, String texto) {
        verificarAbierta();
        elementos.put(localizador, texto);
        acciones.add("escribir en " + localizador);
    }

    /**
     * Hace clic en un elemento (simulado).
     *
     * @param localizador identificador del elemento, por ejemplo "#ingresar"
     */
    protected void hacerClic(String localizador) {
        verificarAbierta();
        acciones.add("clic en " + localizador);
    }

    /**
     * Lee el valor/texto de un elemento.
     *
     * @param localizador identificador del elemento
     * @return el texto, o vacío si el elemento no tiene nada
     */
    protected String leerTexto(String localizador) {
        verificarAbierta();
        return elementos.getOrDefault(localizador, "");
    }

    /**
     * Simula que la aplicación muestra un texto en un elemento (lo que en la vida real haría el sistema).
     *
     * @param localizador elemento donde aparece el texto
     * @param texto       texto que muestra la aplicación
     */
    protected void mostrarTexto(String localizador, String texto) {
        elementos.put(localizador, texto);
    }

    /**
     * Regla común a todas las páginas: no se interactúa con una página que no se ha abierto.
     *
     * @throws IllegalStateException si la página no está abierta
     */
    private void verificarAbierta() {
        if (!abierta) {
            throw new IllegalStateException("Primero hay que abrir la página " + getUrl());
        }
    }

    /**
     * Indica si la página está abierta.
     *
     * @return {@code true} después de llamar {@link #abrir()}
     */
    public boolean isAbierta() {
        return abierta;
    }

    /**
     * Devuelve las acciones realizadas, en solo lectura.
     *
     * @return lista de acciones
     */
    public List<String> getAcciones() {
        return Collections.unmodifiableList(acciones);
    }
}
