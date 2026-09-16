package co.com.semillero.fundamentos.poo.polimorfismo;

import co.com.semillero.fundamentos.variables.EstructurasDeControl;

import java.util.List;

/**
 * Resultado consolidado de una ejecución de pruebas (lo que devuelve {@link EjecutorDePruebas}).
 *
 * <p>Es un objeto <b>inmutable</b>: todos sus atributos son {@code final} y no tiene setters,
 * así que el resumen no se puede alterar después de creado.</p>
 */
public class ResumenEjecucion {

    /** Cantidad total de pruebas ejecutadas. */
    private final int total;

    /** Cantidad de pruebas que pasaron. */
    private final int exitosas;

    /** Nombres de las pruebas que fallaron (lista de solo lectura). */
    private final List<String> nombresFallidas;

    /**
     * Crea el resumen.
     *
     * @param total           pruebas ejecutadas
     * @param exitosas        pruebas que pasaron
     * @param nombresFallidas nombres de las que fallaron
     */
    public ResumenEjecucion(int total, int exitosas, List<String> nombresFallidas) {
        this.total = total;
        this.exitosas = exitosas;
        this.nombresFallidas = List.copyOf(nombresFallidas);
    }

    /**
     * Devuelve el total de pruebas.
     *
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Devuelve cuántas pruebas pasaron.
     *
     * @return exitosas
     */
    public int getExitosas() {
        return exitosas;
    }

    /**
     * Calcula cuántas fallaron (no se guarda: se deriva de los otros datos).
     *
     * @return total menos exitosas
     */
    public int getFallidas() {
        return total - exitosas;
    }

    /**
     * Devuelve los nombres de las pruebas fallidas.
     *
     * @return lista no modificable
     */
    public List<String> getNombresFallidas() {
        return nombresFallidas;
    }

    /**
     * Porcentaje de éxito, reutilizando la lógica del tema 1.
     *
     * @return porcentaje de 0 a 100
     */
    public double getPorcentajeExito() {
        return EstructurasDeControl.calcularPorcentajeExito(exitosas, total);
    }
}
