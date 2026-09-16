package co.com.semillero.certificacion.dummyjson.utils;

import co.com.semillero.certificacion.dummyjson.exceptions.DatoDePruebaNoEncontrado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * LECTOR DE DATOS EXTERNOS (nivel 2 de data driven).
 * Lee un archivo CSV que está en el classpath (carpeta src/test/resources) y lo convierte en una lista de filas.
 * Cada fila es un Map: nombre de columna → valor. Ejemplo: {caso=P01, id=1, titulo=Essence Mascara Lash Princess, ...}
 *
 * Reglas del formato que entiende este lector (simple a propósito, para que se pueda leer y entender):
 * - Primera línea que no sea comentario = encabezados (nombres de las columnas).
 * - Separador: coma. Por eso los valores NO pueden contener comas.
 * - Líneas que empiezan con # son comentarios y se ignoran; las líneas vacías también.
 * - El archivo se lee SIEMPRE en UTF-8 y se quita la marca BOM que a veces agrega Excel.
 */
public final class LectorCsv {

    /** Separador de columnas del archivo. */
    private static final String SEPARADOR = ",";

    /** Símbolo con el que empiezan las líneas de comentario. */
    private static final String COMENTARIO = "#";

    /** Marca BOM (carácter invisible) que Excel pone al inicio de algunos archivos UTF-8. */
    private static final String BOM = "\uFEFF";

    /** Constructor privado: clase de utilidades, solo métodos estáticos. */
    private LectorCsv() {
    }

    /**
     * Lee TODAS las filas del archivo.
     *
     * @param rutaEnClasspath ruta dentro de src/test/resources, por ejemplo "data/productos.csv"
     * @return lista de filas; cada fila es un mapa columna → valor
     */
    public static List<Map<String, String>> leerFilas(String rutaEnClasspath) {
        InputStream archivo = LectorCsv.class.getClassLoader().getResourceAsStream(rutaEnClasspath);
        if (archivo == null) {
            throw new DatoDePruebaNoEncontrado("No se encontró el archivo de datos '" + rutaEnClasspath
                    + "' dentro de src/test/resources");
        }
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(archivo, StandardCharsets.UTF_8))) {
            List<Map<String, String>> filas = new ArrayList<>();
            String[] encabezados = null;
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.replace(BOM, "").trim();
                if (linea.isEmpty() || linea.startsWith(COMENTARIO)) {
                    continue; // Se ignoran a propósito: comentarios y líneas vacías no son datos.
                }
                String[] valores = linea.split(SEPARADOR, -1);
                if (encabezados == null) {
                    encabezados = valores;
                    continue; // La primera línea de datos son los nombres de las columnas.
                }
                if (valores.length != encabezados.length) {
                    throw new DatoDePruebaNoEncontrado("La línea '" + linea + "' de " + rutaEnClasspath + " tiene "
                            + valores.length + " columnas y se esperaban " + encabezados.length
                            + " (¿hay una coma dentro de un valor?)");
                }
                Map<String, String> fila = new LinkedHashMap<>();
                for (int i = 0; i < encabezados.length; i++) {
                    fila.put(encabezados[i].trim(), valores[i].trim());
                }
                filas.add(fila);
            }
            return filas;
        } catch (IOException error) {
            throw new DatoDePruebaNoEncontrado("No se pudo leer el archivo de datos " + rutaEnClasspath, error);
        }
    }

    /**
     * Busca UNA fila por el valor de una columna (por ejemplo, la fila cuyo "caso" es "P01").
     * Si no existe, lanza DatoDePruebaNoEncontrado con un mensaje claro.
     */
    public static Map<String, String> buscarFila(String rutaEnClasspath, String columna, String valor) {
        return leerFilas(rutaEnClasspath).stream()
                .filter(fila -> valor.equals(fila.get(columna)))
                .findFirst()
                .orElseThrow(() -> new DatoDePruebaNoEncontrado("No existe una fila con " + columna + " = '" + valor
                        + "' en " + rutaEnClasspath));
    }
}
