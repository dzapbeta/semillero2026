package co.com.semillero.fundamentos.poo.herencia;

/**
 * Clase HIJA de {@link Navegador}. "Firefox ES UN Navegador".
 *
 * <p>Además de implementar el método abstracto, sobrescribe {@code abrir} para dejar
 * una marca propia, reutilizando la lógica del padre con {@code super.abrir(url)}.</p>
 */
public class Firefox extends Navegador {

    /**
     * Crea un Firefox.
     *
     * @param sinVentana {@code true} para modo headless
     */
    public Firefox(boolean sinVentana) {
        super("Firefox", sinVentana);
    }

    /**
     * Implementa el método abstracto del padre.
     *
     * @return "geckodriver" (el driver de Firefox se llama así)
     */
    @Override
    public String nombreDelDriver() {
        return "geckodriver";
    }

    /**
     * Sobrescribe {@code abrir}: primero hace lo del padre (validar y guardar historial)
     * y luego agrega un detalle propio de Firefox.
     *
     * @param url dirección a abrir
     * @return mensaje del padre + " [perfil limpio de Firefox]"
     */
    @Override
    public String abrir(String url) {
        return super.abrir(url) + " [perfil limpio de Firefox]";
    }
}
