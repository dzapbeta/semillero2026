package co.com.semillero.certificacion.dummyjson.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MODELO de REQUEST: representa el cuerpo JSON que se envía a POST /auth/login.
 * Ejemplo del JSON que produce: {"username":"emilys","password":"emilyspass"}
 *
 * POO - ENCAPSULAMIENTO: los atributos son privados; solo se leen con getters.
 * No hay setters porque unas credenciales no deberían cambiar después de crearse (objeto inmutable).
 */
public class CredencialesLogin {

    /** Nombre de usuario. @JsonProperty indica que en el JSON se llama "username" (en Java lo nombramos en español). */
    @JsonProperty("username")
    private final String usuario;

    /** Contraseña del usuario. En el JSON viaja como "password". */
    @JsonProperty("password")
    private final String clave;

    /**
     * Constructor privado: obliga a crear el objeto con el método de fábrica {@link #de(String, String)},
     * que se lee como una frase ("credenciales de usuario y clave").
     */
    private CredencialesLogin(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    /** Método de fábrica: crea las credenciales con el usuario y la clave recibidos (pueden venir vacíos en casos negativos). */
    public static CredencialesLogin de(String usuario, String clave) {
        return new CredencialesLogin(usuario, clave);
    }

    /** Devuelve el nombre de usuario. */
    public String getUsuario() {
        return usuario;
    }

    /** Devuelve la contraseña. */
    public String getClave() {
        return clave;
    }

    /** Texto que aparece en el reporte y en los logs. NUNCA muestra la clave (buena práctica de seguridad). */
    @Override
    public String toString() {
        return "usuario '" + usuario + "'";
    }
}
