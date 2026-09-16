package co.com.semillero.fundamentos.poo.polimorfismo;

import co.com.semillero.fundamentos.poo.herencia.Navegador;

/**
 * Prueba de interfaz web (simulada, sin abrir un navegador real).
 *
 * <p>Implementa {@link Ejecutable} a su manera: abre la URL en un {@link Navegador} y compara el texto
 * esperado con el texto que "mostró" la página. Recibe CUALQUIER navegador (Chrome, Firefox o Edge):
 * también es polimorfismo, porque la variable es del tipo padre.</p>
 */
public class PruebaWeb implements Ejecutable {

    /** Nombre de la prueba. */
    private final String nombre;

    /** Navegador donde se ejecuta. El tipo es el PADRE: sirve cualquier hijo. */
    private final Navegador navegador;

    /** Dirección de la página bajo prueba. */
    private final String url;

    /** Texto que debería aparecer en la página. */
    private final String textoEsperado;

    /** Texto que la página realmente mostró (simulado para la clase). */
    private final String textoObtenido;

    /**
     * Crea una prueba web.
     *
     * @param nombre        nombre de la prueba
     * @param navegador     navegador a usar (Chrome, Firefox, Edge)
     * @param url           página a abrir
     * @param textoEsperado texto esperado
     * @param textoObtenido texto obtenido (simulado)
     */
    public PruebaWeb(String nombre, Navegador navegador, String url, String textoEsperado, String textoObtenido) {
        this.nombre = nombre;
        this.navegador = navegador;
        this.url = url;
        this.textoEsperado = textoEsperado;
        this.textoObtenido = textoObtenido;
    }

    /**
     * Devuelve el nombre de la prueba.
     *
     * @return nombre
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Abre la página y compara textos con {@code equals}.
     *
     * @return {@code true} si el texto obtenido es igual al esperado
     */
    @Override
    public boolean ejecutar() {
        navegador.abrir(url);
        return textoEsperado.equals(textoObtenido);
    }

    /**
     * Sobrescribe el método {@code default} de la interfaz para incluir el navegador.
     *
     * @return texto como "[Web · Chrome] Login exitoso"
     */
    @Override
    public String describir() {
        return "[Web · " + navegador.getNombre() + "] " + nombre;
    }
}
