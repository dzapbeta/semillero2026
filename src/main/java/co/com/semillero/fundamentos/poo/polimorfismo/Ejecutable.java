package co.com.semillero.fundamentos.poo.polimorfismo;

/**
 * Tema 3 · Polimorfismo. Interfaz = CONTRATO.
 *
 * <p>Una {@code interface} dice QUÉ debe saber hacer una clase, pero no CÓMO. Toda clase que
 * {@code implements Ejecutable} promete tener {@code getNombre()} y {@code ejecutar()}.</p>
 *
 * <p>Gracias a eso, el {@link EjecutorDePruebas} puede tratar igual a una prueba web, de API o móvil:
 * solo le importa que "se puedan ejecutar". Cada una responde a su manera: eso es polimorfismo
 * ("muchas formas").</p>
 */
public interface Ejecutable {

    /**
     * Nombre legible de la prueba.
     *
     * @return nombre, por ejemplo "Login exitoso en Chrome"
     */
    String getNombre();

    /**
     * Ejecuta la prueba. Cada clase lo implementa distinto (web, API, móvil).
     *
     * @return {@code true} si la prueba pasó
     */
    boolean ejecutar();

    /**
     * Método {@code default}: ya trae una implementación que todas las clases heredan,
     * aunque cualquiera la puede sobrescribir.
     *
     * @return texto como "[PruebaApi] Consultar usuario"
     */
    default String describir() {
        return "[" + getClass().getSimpleName() + "] " + getNombre();
    }
}
