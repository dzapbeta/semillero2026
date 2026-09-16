package co.com.semillero.fundamentos.poo.herencia;

/**
 * Clase HIJA de {@link Navegador}. "Edge ES UN Navegador".
 *
 * <p>Muestra el uso de un método {@code protected} heredado ({@code validarUrl}) desde la clase hija
 * para agregar una regla extra: Edge del equipo solo abre sitios seguros (https).</p>
 */
public class Edge extends Navegador {

    /**
     * Crea un Edge.
     *
     * @param sinVentana {@code true} para modo headless
     */
    public Edge(boolean sinVentana) {
        super("Edge", sinVentana);
    }

    /**
     * Implementa el método abstracto del padre.
     *
     * @return "msedgedriver"
     */
    @Override
    public String nombreDelDriver() {
        return "msedgedriver";
    }

    /**
     * Sobrescribe la validación protegida: aplica la regla del padre ({@code super.validarUrl})
     * y además exige https.
     *
     * @param url dirección a validar
     * @throws IllegalArgumentException si la URL no es válida o no es https
     */
    @Override
    protected void validarUrl(String url) {
        super.validarUrl(url);
        if (!url.startsWith("https://")) {
            throw new IllegalArgumentException("Edge solo abre sitios seguros (https): " + url);
        }
    }
}
