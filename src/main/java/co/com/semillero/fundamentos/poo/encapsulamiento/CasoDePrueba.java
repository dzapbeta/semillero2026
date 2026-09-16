package co.com.semillero.fundamentos.poo.encapsulamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tema 2 · Clases, objetos y encapsulamiento.
 *
 * <p>Una <b>clase</b> es el molde (la plantilla de un caso de prueba) y un <b>objeto</b> es cada caso
 * concreto creado con {@code new} (ej. "CP-001 Login exitoso").</p>
 *
 * <p><b>Encapsulamiento</b>: los atributos son {@code private}; nadie de afuera puede dejarlos en un
 * estado inválido. Solo se modifican a través de métodos que VALIDAN las reglas del negocio
 * (por ejemplo, un caso bloqueado no se puede marcar como exitoso).</p>
 */
public class CasoDePrueba {

    /** Identificador único con formato "CP-000". Es {@code final}: una vez creado el caso no cambia. */
    private final String id;

    /** Título descriptivo del caso (qué se prueba). */
    private final String titulo;

    /** Pasos que se siguen para ejecutar el caso, en orden. */
    private final List<String> pasos;

    /** Estado actual del caso. Solo cambia mediante los métodos de ejecución y bloqueo. */
    private EstadoCaso estado;

    /** Cantidad de veces que se ha ejecutado el caso. */
    private int ejecuciones;

    /** Motivo del bloqueo; queda vacío mientras el caso no esté bloqueado. */
    private String motivoBloqueo;

    /**
     * Crea un caso de prueba nuevo en estado {@link EstadoCaso#PENDIENTE}.
     * El constructor es la "puerta de entrada": si los datos no son válidos, el objeto NO se crea.
     *
     * @param id     identificador con formato "CP-000", por ejemplo "CP-001"
     * @param titulo título descriptivo, no vacío
     * @throws IllegalArgumentException si el id no tiene el formato o el título está vacío
     */
    public CasoDePrueba(String id, String titulo) {
        if (id == null || !id.matches("CP-\\d{3}")) {
            throw new IllegalArgumentException("El id debe tener el formato CP-000 y llegó: " + id);
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título del caso no puede estar vacío");
        }
        this.id = id;          // this.id es el atributo; id (sin this) es el parámetro
        this.titulo = titulo.trim();
        this.pasos = new ArrayList<>();
        this.estado = EstadoCaso.PENDIENTE;
        this.ejecuciones = 0;
        this.motivoBloqueo = "";
    }

    /**
     * Agrega un paso al final de la lista de pasos.
     *
     * @param paso descripción del paso, no vacía
     * @throws IllegalArgumentException si el paso está vacío
     */
    public void agregarPaso(String paso) {
        if (paso == null || paso.isBlank()) {
            throw new IllegalArgumentException("El paso no puede estar vacío");
        }
        pasos.add(paso.trim());
    }

    /**
     * Registra el resultado de una ejecución y actualiza el estado y el contador.
     *
     * @param exitoso {@code true} si el resultado obtenido fue el esperado
     * @throws IllegalStateException si el caso está bloqueado o no tiene pasos
     */
    public void registrarEjecucion(boolean exitoso) {
        if (estado == EstadoCaso.BLOQUEADO) {
            throw new IllegalStateException(
                    "El caso " + id + " está bloqueado: " + motivoBloqueo);
        }
        if (pasos.isEmpty()) {
            throw new IllegalStateException(
                    "El caso " + id + " no tiene pasos; no se puede ejecutar");
        }
        ejecuciones++;
        estado = exitoso ? EstadoCaso.EXITOSO : EstadoCaso.FALLIDO;
    }

    /**
     * Bloquea el caso indicando el motivo.
     *
     * @param motivo causa del bloqueo, no vacía (ej. "Ambiente de QA caído")
     * @throws IllegalArgumentException si el motivo está vacío
     */
    public void bloquear(String motivo) {
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("Para bloquear un caso hay que indicar el motivo");
        }
        this.estado = EstadoCaso.BLOQUEADO;
        this.motivoBloqueo = motivo.trim();
    }

    /**
     * Desbloquea el caso y lo deja de nuevo pendiente de ejecución.
     */
    public void desbloquear() {
        this.estado = EstadoCaso.PENDIENTE;
        this.motivoBloqueo = "";
    }

    /**
     * Getter del id. Los getters permiten LEER un atributo privado sin poder cambiarlo.
     *
     * @return identificador del caso
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el título del caso.
     *
     * @return título descriptivo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve los pasos en una lista de SOLO LECTURA.
     * Si devolviéramos la lista original, alguien podría hacer {@code getPasos().clear()} desde afuera
     * y romper el encapsulamiento.
     *
     * @return copia no modificable de los pasos
     */
    public List<String> getPasos() {
        return Collections.unmodifiableList(pasos);
    }

    /**
     * Devuelve el estado actual.
     *
     * @return estado del caso
     */
    public EstadoCaso getEstado() {
        return estado;
    }

    /**
     * Devuelve cuántas veces se ha ejecutado el caso.
     *
     * @return número de ejecuciones
     */
    public int getEjecuciones() {
        return ejecuciones;
    }

    /**
     * Devuelve el motivo del bloqueo.
     *
     * @return motivo, o texto vacío si el caso no está bloqueado
     */
    public String getMotivoBloqueo() {
        return motivoBloqueo;
    }

    /**
     * Representación en texto del objeto. {@code @Override} indica que reemplazamos el
     * {@code toString} heredado de {@code Object} (todas las clases heredan de Object).
     *
     * @return texto como "CP-001 | Login exitoso | EXITOSO | pasos=3 | ejecuciones=1"
     */
    @Override
    public String toString() {
        return id + " | " + titulo + " | " + estado + " | pasos=" + pasos.size() + " | ejecuciones=" + ejecuciones;
    }
}
