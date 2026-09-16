package co.com.semillero.certificacion.saucedemo.models;

import java.util.Arrays;

/**
 * Enumeración con las formas de ordenar los productos en Sauce Demo.
 * Traduce el lenguaje de negocio del feature ("precio de menor a mayor")
 * al valor técnico de la lista desplegable ("lohi").
 * POO - ENCAPSULAMIENTO: cada opción guarda su descripción y su valor en atributos privados.
 */
public enum CriterioDeOrden {

    /** Nombre de la A a la Z (valor "az" en la página). */
    NOMBRE_A_Z("nombre de la A a la Z", "az"),
    /** Nombre de la Z a la A (valor "za" en la página). */
    NOMBRE_Z_A("nombre de la Z a la A", "za"),
    /** Precio de menor a mayor (valor "lohi" en la página). */
    PRECIO_MENOR_A_MAYOR("precio de menor a mayor", "lohi"),
    /** Precio de mayor a menor (valor "hilo" en la página). */
    PRECIO_MAYOR_A_MENOR("precio de mayor a menor", "hilo");

    /** Texto en español que se escribe en el feature. */
    private final String descripcion;

    /** Valor del atributo value de la opción en la lista desplegable. */
    private final String valor;

    /** Constructor del enum: se ejecuta una vez por cada opción de arriba. */
    CriterioDeOrden(String descripcion, String valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    /** Devuelve el valor técnico que se selecciona en la lista ("az", "lohi", ...). */
    public String getValor() {
        return valor;
    }

    /**
     * Busca la opción cuya descripción coincide con el texto del feature.
     * Si el texto no existe, lanza un error que lista las opciones válidas.
     */
    public static CriterioDeOrden desdeDescripcion(String descripcion) {
        return Arrays.stream(values())
                .filter(criterio -> criterio.descripcion.equalsIgnoreCase(descripcion))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Criterio de orden no soportado: '"
                        + descripcion + "'. Use: " + Arrays.toString(values())));
    }

    /** Texto legible de la opción (se ve en el reporte de Serenity). */
    @Override
    public String toString() {
        return descripcion;
    }
}
