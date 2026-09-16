package co.com.semillero.certificacion.dummyjson.models;

import java.util.Map;

/**
 * MODELO de DATOS DE PRUEBA: una fila del archivo src/test/resources/data/productos.csv.
 * Guarda lo que ESPERAMOS que devuelva la API para un caso (nivel 2 de data driven).
 *
 * POO - ENCAPSULAMIENTO: atributos privados y finales; solo lectura por getters.
 */
public class ProductoEsperado {

    /** Identificador del caso en el CSV (columna "caso"), por ejemplo "P01". Es lo que se escribe en los Ejemplos del feature. */
    private final String caso;

    /** Id del producto que se va a consultar (columna "id"). */
    private final int id;

    /** Título que debe devolver la API (columna "titulo"). */
    private final String titulo;

    /** Categoría que debe devolver la API (columna "categoria"). */
    private final String categoria;

    /** Precio que debe devolver la API (columna "precio"). */
    private final double precio;

    /** Constructor con todos los datos; privado para crear el objeto desde una fila del CSV con {@link #desdeFila(Map)}. */
    private ProductoEsperado(String caso, int id, String titulo, String categoria, double precio) {
        this.caso = caso;
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.precio = precio;
    }

    /**
     * Convierte una fila del CSV (mapa columna → valor) en un ProductoEsperado.
     * Integer.parseInt y Double.parseDouble convierten el texto del archivo en números.
     */
    public static ProductoEsperado desdeFila(Map<String, String> fila) {
        return new ProductoEsperado(
                fila.get("caso"),
                Integer.parseInt(fila.get("id")),
                fila.get("titulo"),
                fila.get("categoria"),
                Double.parseDouble(fila.get("precio")));
    }

    /** Devuelve el identificador del caso. */
    public String getCaso() {
        return caso;
    }

    /** Devuelve el id del producto. */
    public int getId() {
        return id;
    }

    /** Devuelve el título esperado. */
    public String getTitulo() {
        return titulo;
    }

    /** Devuelve la categoría esperada. */
    public String getCategoria() {
        return categoria;
    }

    /** Devuelve el precio esperado. */
    public double getPrecio() {
        return precio;
    }

    /** Texto legible del caso para el reporte. */
    @Override
    public String toString() {
        return "caso " + caso + " (producto " + id + ")";
    }
}
