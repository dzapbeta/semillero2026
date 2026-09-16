package co.com.semillero.fundamentos.poo.encapsulamiento;

/**
 * Estados posibles de un caso de prueba.
 *
 * <p>Un {@code enum} es un tipo con una lista CERRADA de valores. Usarlo en vez de un {@code String}
 * evita errores de digitación: {@code "EXITOSo"} compila, pero {@code EstadoCaso.EXITOSo} no.</p>
 */
public enum EstadoCaso {

    /** El caso está diseñado pero todavía no se ha ejecutado. */
    PENDIENTE("Pendiente de ejecución"),

    /** El caso se ejecutó y el resultado obtenido es igual al esperado. */
    EXITOSO("Ejecutado con éxito"),

    /** El caso se ejecutó y el resultado obtenido NO es el esperado. */
    FALLIDO("Ejecutado con fallo"),

    /** El caso no se puede ejecutar por una causa externa (ambiente caído, defecto previo...). */
    BLOQUEADO("Bloqueado");

    /** Texto legible del estado, útil para reportes. Es {@code final}: no cambia nunca. */
    private final String descripcion;

    /**
     * Constructor del enum: se llama automáticamente una vez por cada valor de la lista de arriba.
     *
     * @param descripcion texto legible del estado
     */
    EstadoCaso(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el texto legible del estado.
     *
     * @return descripción, por ejemplo "Ejecutado con éxito"
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Indica si el estado es el resultado de una ejecución (exitoso o fallido).
     *
     * @return {@code true} para EXITOSO y FALLIDO
     */
    public boolean fueEjecutado() {
        return this == EXITOSO || this == FALLIDO;
    }
}
