package co.com.semillero.certificacion.dummyjson.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// Lee un archivo CSV sencillo de dos columnas: id,valor
// Así los datos de prueba viven en un archivo y no dentro del código.
public class LectorCsv {

    // Constructor privado: solo usamos el método estático de abajo.
    private LectorCsv() {
    }

    // Busca la fila cuyo id sea igual al que llega y devuelve la segunda columna.
    // La primera línea del archivo son los títulos de las columnas, por eso se salta.
    public static String buscarValor(String rutaArchivo, String id) {
        List<String> lineas = leerLineas(rutaArchivo);
        for (int i = 1; i < lineas.size(); i++) {
            String[] columnas = lineas.get(i).split(",");
            if (columnas[0].trim().equals(id.trim())) {
                return columnas[1].trim();
            }
        }
        throw new IllegalArgumentException("No encontré el id " + id + " en el archivo " + rutaArchivo);
    }

    // Lee todas las líneas del archivo en UTF-8. Si el archivo no existe, avisa con un mensaje claro.
    private static List<String> leerLineas(String rutaArchivo) {
        try {
            return Files.readAllLines(Path.of(rutaArchivo), StandardCharsets.UTF_8);
        } catch (IOException error) {
            throw new IllegalStateException("No pude leer el archivo " + rutaArchivo, error);
        }
    }
}
