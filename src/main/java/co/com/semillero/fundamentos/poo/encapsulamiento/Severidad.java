package co.com.semillero.fundamentos.poo.encapsulamiento;

/**
 * Severidad de un defecto: qué tanto daño hace al sistema.
 *
 * <p>Muestra que un {@code enum} también puede tener atributos ({@code nivel}) y métodos.</p>
 */
public enum Severidad {

    /** Bloquea una funcionalidad principal sin alternativa (ej. nadie puede iniciar sesión). */
    CRITICA(4),

    /** Afecta una funcionalidad importante, pero existe un camino alterno. */
    ALTA(3),

    /** Afecta una funcionalidad secundaria. */
    MEDIA(2),

    /** Detalle menor o cosmético (ej. un texto mal alineado). */
    BAJA(1);

    /** Número que permite comparar severidades: 4 es la más grave, 1 la más leve. */
    private final int nivel;

    /**
     * Constructor del enum.
     *
     * @param nivel número de gravedad (1 a 4)
     */
    Severidad(int nivel) {
        this.nivel = nivel;
    }

    /**
     * Devuelve el nivel numérico de gravedad.
     *
     * @return 4 (CRITICA) a 1 (BAJA)
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Indica si un defecto de esta severidad impide salir a producción.
     *
     * @return {@code true} para CRITICA y ALTA
     */
    public boolean bloqueaSalidaAProduccion() {
        return nivel >= ALTA.nivel;
    }
}
