package co.com.semillero.certificacion.dummyjson.models;

import com.fasterxml.jackson.annotation.JsonProperty;

// Representa el cuerpo que se envía para iniciar sesión.
// Serenity lo convierte en este JSON: {"username": "...", "password": "..."}
public class Credenciales {

    // Nombre de usuario. @JsonProperty dice cómo se llama el campo en el JSON que espera la API.
    @JsonProperty("username")
    private final String usuario;

    // Clave del usuario. En el JSON se llama "password".
    @JsonProperty("password")
    private final String clave;

    // Crea las credenciales con el usuario y la clave que vienen del feature.
    public Credenciales(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
}
