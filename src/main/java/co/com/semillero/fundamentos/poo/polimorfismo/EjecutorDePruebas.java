package co.com.semillero.fundamentos.poo.polimorfismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ejecuta un lote de pruebas de cualquier tipo. Aquí se ve el <b>polimorfismo dinámico</b>.
 *
 * <p>La lista es de tipo {@code List<Ejecutable>}: el ejecutor NO sabe si cada elemento es web,
 * API o móvil. Llama {@code prueba.ejecutar()} y Java decide EN TIEMPO DE EJECUCIÓN qué versión
 * del método correr, según el objeto real. Agregar un tipo nuevo de prueba no obliga a tocar esta clase.</p>
 */
public class EjecutorDePruebas {

    /** Bitácora con una línea por prueba ejecutada, en orden. */
    private final List<String> bitacora = new ArrayList<>();

    /**
     * Crea un ejecutor con la bitácora vacía. Si no se escribe ningún constructor, Java crea uno
     * igual a este automáticamente (el "constructor por defecto").
     */
    public EjecutorDePruebas() {
        // no necesita datos: la bitácora ya se inicializó arriba
    }

    /**
     * Ejecuta todas las pruebas y construye el resumen.
     *
     * @param pruebas lista de pruebas de cualquier tipo que implemente {@link Ejecutable}
     * @return resumen con total, exitosas y nombres de las fallidas
     * @throws IllegalArgumentException si la lista es nula
     */
    public ResumenEjecucion ejecutarTodas(List<? extends Ejecutable> pruebas) {
        if (pruebas == null) {
            throw new IllegalArgumentException("La lista de pruebas no puede ser nula");
        }
        int exitosas = 0;
        List<String> fallidas = new ArrayList<>();
        for (Ejecutable prueba : pruebas) {
            boolean paso = prueba.ejecutar(); // <- llamada polimórfica
            bitacora.add((paso ? "PASÓ  " : "FALLÓ ") + prueba.describir());
            if (paso) {
                exitosas++;
            } else {
                fallidas.add(prueba.getNombre());
            }
        }
        return new ResumenEjecucion(pruebas.size(), exitosas, fallidas);
    }

    /**
     * Devuelve la bitácora de ejecución.
     *
     * @return lista de solo lectura con una línea por prueba
     */
    public List<String> getBitacora() {
        return Collections.unmodifiableList(bitacora);
    }
}
