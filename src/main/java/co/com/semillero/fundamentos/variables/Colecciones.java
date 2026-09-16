package co.com.semillero.fundamentos.variables;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Tema 1 · Arreglos y colecciones.
 *
 * <ul>
 *   <li><b>Arreglo</b> ({@code String[]}): tamaño fijo, se accede por posición.</li>
 *   <li><b>List</b>: lista ordenada que crece; admite repetidos (ej. casos de un ciclo).</li>
 *   <li><b>Set</b>: conjunto SIN repetidos (ej. defectos únicos).</li>
 *   <li><b>Map</b>: pares clave → valor (ej. estado → cantidad de casos).</li>
 * </ul>
 */
public final class Colecciones {

    /**
     * Arreglo constante con los navegadores soportados por el equipo.
     * Un arreglo tiene tamaño fijo: aquí siempre serán 3 posiciones (0, 1 y 2).
     */
    public static final String[] NAVEGADORES_SOPORTADOS = {"Chrome", "Firefox", "Edge"};

    /** Constructor privado: clase de utilidades, solo tiene métodos {@code static}. */
    private Colecciones() {
    }

    /**
     * Revisa si un navegador está soportado recorriendo el arreglo.
     *
     * @param navegador nombre a buscar (no distingue mayúsculas de minúsculas)
     * @return {@code true} si está en {@link #NAVEGADORES_SOPORTADOS}
     */
    public static boolean esNavegadorSoportado(String navegador) {
        for (String soportado : NAVEGADORES_SOPORTADOS) {
            if (soportado.equalsIgnoreCase(navegador)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Filtra los casos cuyo nombre empieza por un prefijo (por ejemplo, todos los de "Login").
     * Ilustra {@code List}: se crea vacía con {@code new ArrayList<>()} y crece con {@code add}.
     *
     * @param casos   lista con nombres de casos
     * @param prefijo texto con el que deben empezar
     * @return una lista NUEVA con los casos que cumplen (la original no se modifica)
     */
    public static List<String> filtrarPorPrefijo(List<String> casos, String prefijo) {
        List<String> filtrados = new ArrayList<>();
        for (String caso : casos) {
            if (caso.startsWith(prefijo)) {
                filtrados.add(caso);
            }
        }
        return filtrados;
    }

    /**
     * Cuenta cuántos casos hay por estado.
     * Ilustra {@code Map}: {@code getOrDefault} lee el valor actual (o 0 si la clave no existe)
     * y {@code put} guarda el nuevo valor. {@code LinkedHashMap} conserva el orden de llegada.
     *
     * @param estados lista de estados, por ejemplo ["EXITOSO", "FALLIDO", "EXITOSO"]
     * @return mapa estado → cantidad, por ejemplo {EXITOSO=2, FALLIDO=1}
     */
    public static Map<String, Integer> contarPorEstado(List<String> estados) {
        Map<String, Integer> conteo = new LinkedHashMap<>();
        for (String estado : estados) {
            conteo.put(estado, conteo.getOrDefault(estado, 0) + 1);
        }
        return conteo;
    }

    /**
     * Elimina defectos reportados dos veces.
     * Ilustra {@code Set}: al agregar un elemento repetido, el conjunto simplemente lo ignora.
     * {@code LinkedHashSet} además conserva el orden en que se agregaron.
     *
     * @param idsDefectos ids de defectos, posiblemente repetidos
     * @return conjunto con cada id una sola vez
     */
    public static Set<String> eliminarDuplicados(List<String> idsDefectos) {
        return new LinkedHashSet<>(idsDefectos);
    }

    /**
     * Imprime en consola ejemplos de arreglos, listas, mapas y conjuntos. Lo usa {@code App}.
     */
    public static void demostrar() {
        System.out.println("Arreglo -> posición 0: " + NAVEGADORES_SOPORTADOS[0]
                + " | tamaño: " + NAVEGADORES_SOPORTADOS.length
                + " | ¿Safari soportado? " + esNavegadorSoportado("Safari"));
        List<String> casos = List.of("Login exitoso", "Login con clave errada", "Buscar producto", "Login bloqueado");
        System.out.println("List -> casos de Login: " + filtrarPorPrefijo(casos, "Login"));
        List<String> estados = List.of("EXITOSO", "FALLIDO", "EXITOSO", "BLOQUEADO", "EXITOSO");
        System.out.println("Map  -> conteo por estado: " + contarPorEstado(estados));
        List<String> defectos = List.of("BUG-10", "BUG-11", "BUG-10", "BUG-12", "BUG-11");
        System.out.println("Set  -> defectos únicos: " + eliminarDuplicados(defectos));
    }
}
