package co.com.semillero.playwright.models;

/**
 * Modelo (POJO) con los datos que pide el checkout de saucedemo: nombre, apellido y código postal.
 * <p>
 * Agrupar los tres datos en un objeto evita métodos con muchos parámetros String
 * (fácil de confundir el orden) y deja la prueba más legible.
 */
public class DatosDeEnvio {

    /** Nombre de quien recibe el pedido (campo "First Name"). */
    private final String nombre;

    /** Apellido de quien recibe el pedido (campo "Last Name"). */
    private final String apellido;

    /** Código postal de entrega (campo "Zip/Postal Code"). */
    private final String codigoPostal;

    /**
     * Constructor: crea los datos de envío. Los nulos se convierten en texto vacío
     * para poder probar el formulario con campos faltantes.
     *
     * @param nombre       nombre de quien recibe
     * @param apellido     apellido de quien recibe
     * @param codigoPostal código postal de entrega
     */
    public DatosDeEnvio(String nombre, String apellido, String codigoPostal) {
        this.nombre = nombre == null ? "" : nombre;
        this.apellido = apellido == null ? "" : apellido;
        this.codigoPostal = codigoPostal == null ? "" : codigoPostal;
    }

    /** @return el nombre de quien recibe */
    public String getNombre() {
        return nombre;
    }

    /** @return el apellido de quien recibe */
    public String getApellido() {
        return apellido;
    }

    /** @return el código postal de entrega */
    public String getCodigoPostal() {
        return codigoPostal;
    }
}
