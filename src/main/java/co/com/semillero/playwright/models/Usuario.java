package co.com.semillero.playwright.models;

/**
 * Modelo (POJO) que representa a un usuario de saucedemo.com.
 * <p>
 * Un POJO ("Plain Old Java Object") solo guarda datos: no abre navegadores ni hace clics.
 * Así las pruebas hablan de "un usuario" en lugar de pasar dos Strings sueltos.
 * <b>Encapsulamiento:</b> los atributos son privados y solo se leen con los getters.
 */
public class Usuario {

    /** Nombre con el que el usuario inicia sesión (ej. standard_user). Privado: nadie lo cambia desde fuera. */
    private final String nombreUsuario;

    /** Contraseña del usuario (en saucedemo todos usan secret_sauce). Privada y de solo lectura. */
    private final String clave;

    /**
     * Constructor: crea un usuario con su nombre y su clave.
     * Si llega un valor nulo (celda vacía en un CSV) se guarda como texto vacío para poder escribirlo.
     *
     * @param nombreUsuario nombre de usuario que se escribe en el formulario
     * @param clave         contraseña que se escribe en el formulario
     */
    public Usuario(String nombreUsuario, String clave) {
        this.nombreUsuario = nombreUsuario == null ? "" : nombreUsuario;
        this.clave = clave == null ? "" : clave;
    }

    /**
     * Método de fábrica: atajo para crear el usuario estándar de saucedemo.
     *
     * @return un usuario válido (standard_user / secret_sauce)
     */
    public static Usuario estandar() {
        return new Usuario("standard_user", "secret_sauce");
    }

    /**
     * Método de fábrica: atajo para crear el usuario bloqueado de saucedemo.
     *
     * @return un usuario que existe pero está bloqueado (locked_out_user / secret_sauce)
     */
    public static Usuario bloqueado() {
        return new Usuario("locked_out_user", "secret_sauce");
    }

    /** @return el nombre de usuario (getter: única forma de leer el atributo privado) */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /** @return la clave del usuario */
    public String getClave() {
        return clave;
    }
}
