package co.com.semillero.fundamentos.poo.polimorfismo;

/**
 * Prueba de API (simulada, sin llamadas HTTP reales).
 *
 * <p>Implementa {@link Ejecutable} comparando el código de estado HTTP esperado con el obtenido.</p>
 */
public class PruebaApi implements Ejecutable {

    /** Nombre de la prueba. */
    private final String nombre;

    /** Recurso consumido, por ejemplo "GET /usuarios/1". */
    private final String endpoint;

    /** Código HTTP esperado, por ejemplo 200. */
    private final int codigoEsperado;

    /** Código HTTP que respondió el servicio (simulado). */
    private final int codigoObtenido;

    /**
     * Crea una prueba de API.
     *
     * @param nombre         nombre de la prueba
     * @param endpoint       método y ruta, por ejemplo "GET /usuarios/1"
     * @param codigoEsperado código HTTP esperado
     * @param codigoObtenido código HTTP obtenido (simulado)
     */
    public PruebaApi(String nombre, String endpoint, int codigoEsperado, int codigoObtenido) {
        this.nombre = nombre;
        this.endpoint = endpoint;
        this.codigoEsperado = codigoEsperado;
        this.codigoObtenido = codigoObtenido;
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
     * Compara códigos con {@code ==} (correcto para primitivos {@code int}).
     *
     * @return {@code true} si el código obtenido es el esperado
     */
    @Override
    public boolean ejecutar() {
        return codigoEsperado == codigoObtenido;
    }

    /**
     * Devuelve el endpoint probado.
     *
     * @return método y ruta
     */
    public String getEndpoint() {
        return endpoint;
    }
}
