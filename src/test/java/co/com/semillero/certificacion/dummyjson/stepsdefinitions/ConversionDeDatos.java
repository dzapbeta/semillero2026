package co.com.semillero.certificacion.dummyjson.stepsdefinitions;

import co.com.semillero.certificacion.dummyjson.models.Producto;
import io.cucumber.java.DataTableType;

import java.util.Map;

/**
 * CONVERSIÓN DE DATOS (nivel 3 de data driven).
 * Le enseña a Cucumber a convertir cada fila de un DataTable en un objeto Producto.
 * Gracias a esto el step recibe directamente un Producto y no un DataTable "crudo".
 */
public class ConversionDeDatos {

    /**
     * @DataTableType: Cucumber llama a este método por cada fila de la tabla.
     * La fila llega como mapa: encabezado de la columna → valor de la celda (todo como texto).
     * Aquí se convierte cada texto al tipo correcto (Double, Integer).
     */
    @DataTableType
    public Producto productoDesdeFila(Map<String, String> fila) {
        Producto producto = new Producto();
        producto.setTitulo(fila.get("titulo"));
        producto.setCategoria(fila.get("categoria"));
        producto.setPrecio(Double.valueOf(fila.get("precio")));
        producto.setMarca(fila.get("marca"));
        producto.setStock(Integer.valueOf(fila.get("stock")));
        return producto;
    }
}
