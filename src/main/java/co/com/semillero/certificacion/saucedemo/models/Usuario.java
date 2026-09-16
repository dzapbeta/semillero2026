package co.com.semillero.certificacion.saucedemo.models;

/**
 * Modelo (POJO) que representa a un usuario de Sauce Demo.
 * POO - ENCAPSULAMIENTO: los atributos son privados y solo se leen con getters.
 * No tiene setters: una vez creado, el usuario no cambia (objeto inmutable).
 */
public class Usuario {

    /** Nombre con el que el usuario inicia sesión (por ejemplo "standard_user"). */
    private final String nombreDeUsuario;

    /** Contraseña del usuario (en Sauce Demo es "secret_sauce"). */
    private final String clave;

    /**
     * Constructor: crea un usuario con su nombre y su clave.
     * Es el único lugar donde se asignan los atributos.
     */
    public Usuario(String nombreDeUsuario, String clave) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.clave = clave;
    }

    /** Devuelve el nombre de usuario para escribirlo en el formulario de login. */
    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    /** Devuelve la clave para escribirla en el formulario de login. */
    public String getClave() {
        return clave;
    }

    /**
     * Texto que se muestra cuando se imprime el objeto (por ejemplo en el reporte).
     * POO - POLIMORFISMO: sobrescribe (@Override) el método toString() heredado de Object.
     * La clave NO se muestra para no exponer información sensible.
     */
    @Override
    public String toString() {
        return nombreDeUsuario;
    }
}
