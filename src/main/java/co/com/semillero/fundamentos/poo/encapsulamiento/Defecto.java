package co.com.semillero.fundamentos.poo.encapsulamiento;

import java.util.Objects;

/**
 * Defecto (bug) encontrado al ejecutar un caso de prueba.
 *
 * <p>Muestra encapsulamiento y además <b>asociación entre objetos</b>: un defecto "tiene un"
 * {@link CasoDePrueba} que lo detectó.</p>
 */
public class Defecto {

    /** Longitud mínima del título para que el defecto se entienda sin abrirlo. */
    public static final int LONGITUD_MINIMA_TITULO = 10;

    /** Identificador del defecto, por ejemplo "BUG-101". */
    private final String id;

    /** Resumen corto de lo que falla. */
    private final String titulo;

    /** Gravedad del defecto; puede recalificarse con {@link #cambiarSeveridad(Severidad)}. */
    private Severidad severidad;

    /** Caso de prueba que detectó el defecto (asociación "tiene un"). */
    private final CasoDePrueba casoQueLoDetecto;

    /** {@code true} mientras el defecto no se haya corregido y cerrado. */
    private boolean abierto;

    /**
     * Crea un defecto abierto.
     *
     * @param id               identificador con formato "BUG-" seguido de números
     * @param titulo           resumen de al menos {@value #LONGITUD_MINIMA_TITULO} caracteres
     * @param severidad        gravedad, no nula
     * @param casoQueLoDetecto caso de prueba que lo encontró, no nulo
     * @throws IllegalArgumentException si algún dato es inválido
     */
    public Defecto(String id, String titulo, Severidad severidad, CasoDePrueba casoQueLoDetecto) {
        if (id == null || !id.matches("BUG-\\d+")) {
            throw new IllegalArgumentException("El id del defecto debe tener el formato BUG-123 y llegó: " + id);
        }
        if (titulo == null || titulo.trim().length() < LONGITUD_MINIMA_TITULO) {
            throw new IllegalArgumentException("El título debe tener al menos " + LONGITUD_MINIMA_TITULO + " caracteres");
        }
        this.id = id;
        this.titulo = titulo.trim();
        this.severidad = Objects.requireNonNull(severidad, "La severidad es obligatoria");
        this.casoQueLoDetecto = Objects.requireNonNull(casoQueLoDetecto, "El caso que detectó el defecto es obligatorio");
        this.abierto = true;
    }

    /**
     * Cierra el defecto cuando la corrección fue validada.
     *
     * @throws IllegalStateException si el defecto ya estaba cerrado
     */
    public void cerrar() {
        if (!abierto) {
            throw new IllegalStateException("El defecto " + id + " ya está cerrado");
        }
        abierto = false;
    }

    /**
     * Reabre el defecto cuando el error vuelve a aparecer (re-test fallido).
     *
     * @throws IllegalStateException si el defecto ya estaba abierto
     */
    public void reabrir() {
        if (abierto) {
            throw new IllegalStateException("El defecto " + id + " ya está abierto");
        }
        abierto = true;
    }

    /**
     * Recalifica la severidad (por ejemplo, tras hablar con negocio).
     *
     * @param nuevaSeveridad severidad nueva, no nula
     */
    public void cambiarSeveridad(Severidad nuevaSeveridad) {
        this.severidad = Objects.requireNonNull(nuevaSeveridad, "La severidad es obligatoria");
    }

    /**
     * Indica si este defecto impide salir a producción: debe estar abierto y ser CRITICA o ALTA.
     *
     * @return {@code true} si bloquea la salida
     */
    public boolean bloqueaSalida() {
        return abierto && severidad.bloqueaSalidaAProduccion();
    }

    /**
     * Devuelve el id del defecto.
     *
     * @return identificador
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el título del defecto.
     *
     * @return resumen corto
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve la severidad actual.
     *
     * @return severidad
     */
    public Severidad getSeveridad() {
        return severidad;
    }

    /**
     * Devuelve el caso de prueba que detectó el defecto.
     *
     * @return caso asociado
     */
    public CasoDePrueba getCasoQueLoDetecto() {
        return casoQueLoDetecto;
    }

    /**
     * Indica si el defecto sigue abierto. Para {@code boolean} la convención es {@code isAlgo()}.
     *
     * @return {@code true} si está abierto
     */
    public boolean isAbierto() {
        return abierto;
    }

    /**
     * Representación en texto del defecto.
     *
     * @return texto como "BUG-101 [CRITICA] Botón pagar no responde (caso CP-003) - ABIERTO"
     */
    @Override
    public String toString() {
        return id + " [" + severidad + "] " + titulo + " (caso " + casoQueLoDetecto.getId() + ") - "
                + (abierto ? "ABIERTO" : "CERRADO");
    }
}
