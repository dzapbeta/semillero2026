package co.com.semillero.fundamentos.poo.polimorfismo;

/**
 * Prueba de aplicación móvil (simulada, sin dispositivo real).
 *
 * <p>Implementa {@link Ejecutable} revisando si un elemento quedó visible en la pantalla del dispositivo.</p>
 */
public class PruebaMovil implements Ejecutable {

    /** Nombre de la prueba. */
    private final String nombre;

    /** Dispositivo donde se ejecuta, por ejemplo "Android 14 - Pixel 8". */
    private final String dispositivo;

    /** Si el elemento esperado quedó visible (simulado). */
    private final boolean elementoVisible;

    /**
     * Crea una prueba móvil.
     *
     * @param nombre          nombre de la prueba
     * @param dispositivo     dispositivo de ejecución
     * @param elementoVisible {@code true} si el elemento esperado apareció
     */
    public PruebaMovil(String nombre, String dispositivo, boolean elementoVisible) {
        this.nombre = nombre;
        this.dispositivo = dispositivo;
        this.elementoVisible = elementoVisible;
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
     * Pasa si el elemento esperado está visible.
     *
     * @return {@code true} si el elemento es visible
     */
    @Override
    public boolean ejecutar() {
        return elementoVisible;
    }

    /**
     * Devuelve el dispositivo de ejecución.
     *
     * @return dispositivo
     */
    public String getDispositivo() {
        return dispositivo;
    }
}
