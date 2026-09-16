package co.com.semillero.fundamentos.poo.herencia;

/**
 * Clase HIJA de {@link Navegador}. "Chrome ES UN Navegador".
 *
 * <p>Hereda {@code abrir}, {@code getHistorial}, etc. y agrega lo propio de Chrome: el modo incógnito.</p>
 */
public class Chrome extends Navegador {

    /** Si Chrome abre en modo incógnito (sin cookies previas). Atributo que solo existe en Chrome. */
    private final boolean incognito;

    /**
     * Crea un Chrome. {@code super(...)} llama al constructor del padre para inicializar lo heredado.
     *
     * @param sinVentana {@code true} para modo headless
     * @param incognito  {@code true} para modo incógnito
     */
    public Chrome(boolean sinVentana, boolean incognito) {
        super("Chrome", sinVentana);
        this.incognito = incognito;
    }

    /**
     * Implementa el método abstracto del padre.
     *
     * @return "chromedriver"
     */
    @Override
    public String nombreDelDriver() {
        return "chromedriver";
    }

    /**
     * Sobrescribe {@code describir}: reutiliza el texto del padre con {@code super.describir()}
     * y le agrega el dato de incógnito.
     *
     * @return descripción completa de Chrome
     */
    @Override
    public String describir() {
        return super.describir() + " | incognito=" + incognito;
    }

    /**
     * Indica si está en modo incógnito.
     *
     * @return {@code true} si es incógnito
     */
    public boolean isIncognito() {
        return incognito;
    }
}
