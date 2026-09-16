package co.com.semillero.fundamentos.poo.polimorfismo;

import co.com.semillero.fundamentos.poo.encapsulamiento.CasoDePrueba;
import co.com.semillero.fundamentos.poo.encapsulamiento.Defecto;

/**
 * Genera líneas de reporte. Aquí se ve el <b>polimorfismo estático</b> o <b>sobrecarga</b> (overloading).
 *
 * <p>Hay varios métodos con el MISMO nombre {@code formatear} pero con parámetros DIFERENTES.
 * Java elige cuál usar AL COMPILAR, mirando el tipo y la cantidad de argumentos.
 * No confundir con la sobrescritura ({@code @Override}), que ocurre entre padre e hijo y se decide
 * al ejecutar.</p>
 */
public class Reportero {

    /** Crea un reportero. No tiene atributos, así que no hay nada que inicializar. */
    public Reportero() {
        // constructor vacío escrito a propósito para documentarlo
    }

    /**
     * Sobrecarga 1: formatea un caso de prueba.
     *
     * @param caso caso a reportar
     * @return texto como "CASO CP-001 - Login exitoso: EXITOSO"
     */
    public String formatear(CasoDePrueba caso) {
        return "CASO " + caso.getId() + " - " + caso.getTitulo() + ": " + caso.getEstado();
    }

    /**
     * Sobrecarga 2: formatea un defecto.
     *
     * @param defecto defecto a reportar
     * @return texto como "DEFECTO BUG-101 (CRITICA) - Botón pagar no responde"
     */
    public String formatear(Defecto defecto) {
        return "DEFECTO " + defecto.getId() + " (" + defecto.getSeveridad() + ") - " + defecto.getTitulo();
    }

    /**
     * Sobrecarga 3: formatea un resultado suelto a partir de un nombre y un booleano.
     *
     * @param nombre nombre de la prueba
     * @param paso   {@code true} si pasó
     * @return texto como "Login exitoso -> OK"
     */
    public String formatear(String nombre, boolean paso) {
        return nombre + " -> " + (paso ? "OK" : "KO");
    }

    /**
     * Sobrecarga 4: formatea el resumen de una ejecución completa.
     *
     * @param resumen resumen a reportar
     * @return texto como "Total: 5 | Exitosas: 4 | Fallidas: 1 | Éxito: 80.0 %"
     */
    public String formatear(ResumenEjecucion resumen) {
        return "Total: " + resumen.getTotal() + " | Exitosas: " + resumen.getExitosas()
                + " | Fallidas: " + resumen.getFallidas() + " | Éxito: " + resumen.getPorcentajeExito() + " %";
    }
}
