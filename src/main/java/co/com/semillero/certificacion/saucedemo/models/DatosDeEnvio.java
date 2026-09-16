package co.com.semillero.certificacion.saucedemo.models;

/**
 * Modelo (POJO) con los datos que pide el checkout de Sauce Demo:
 * nombre, apellido y código postal.
 * POO - ENCAPSULAMIENTO: atributos privados, acceso solo por getters.
 */
public class DatosDeEnvio {

    /** Nombre de la persona que recibe el pedido. */
    private final String nombre;

    /** Apellido de la persona que recibe el pedido. */
    private final String apellido;

    /** Código postal de la dirección de entrega. */
    private final String codigoPostal;

    /** Constructor: arma los datos de envío completos de una sola vez. */
    public DatosDeEnvio(String nombre, String apellido, String codigoPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoPostal = codigoPostal;
    }

    /** Devuelve el nombre para el campo "First Name". */
    public String getNombre() {
        return nombre;
    }

    /** Devuelve el apellido para el campo "Last Name". */
    public String getApellido() {
        return apellido;
    }

    /** Devuelve el código postal para el campo "Zip/Postal Code". */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /** Texto legible del objeto (POO - POLIMORFISMO: sobrescribe toString de Object). */
    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + codigoPostal + ")";
    }
}
