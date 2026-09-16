package co.com.semillero.playwright.tests;

import co.com.semillero.playwright.pages.LoginPage;
import co.com.semillero.playwright.pages.ProductosPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Pruebas del inicio de sesión en saucedemo.com. Son los mismos flujos de la clase 2, ahora con POM.
public class LoginTest extends BaseTest {

    // Un usuario válido entra y ve la pantalla de productos.
    @Test
    @DisplayName("Login exitoso con usuario estándar")
    void loginExitoso() {
        LoginPage login = new LoginPage(pagina);
        login.abrir();
        login.iniciarSesion("standard_user", "secret_sauce");

        ProductosPage productos = new ProductosPage(pagina);
        assertEquals("Products", productos.titulo());
    }

    // Un usuario bloqueado no entra y ve el mensaje de bloqueo.
    @Test
    @DisplayName("Login con usuario bloqueado muestra mensaje de error")
    void loginUsuarioBloqueado() {
        LoginPage login = new LoginPage(pagina);
        login.abrir();
        login.iniciarSesion("locked_out_user", "secret_sauce");

        assertEquals("Epic sadface: Sorry, this user has been locked out.", login.mensajeDeError());
    }

    // La misma prueba se repite una vez por cada fila de @CsvSource (data driven).
    // Cada fila trae: usuario, clave y mensaje esperado. '' significa campo vacío.
    @DisplayName("Login inválido muestra el mensaje correcto")
    @ParameterizedTest(name = "Login inválido: usuario ''{0}'', clave ''{1}''")
    @CsvSource({
            "standard_user, clave_mala,   Epic sadface: Username and password do not match any user in this service",
            "'',            secret_sauce, Epic sadface: Username is required",
            "standard_user, '',           Epic sadface: Password is required"
    })
    void loginInvalido(String usuario, String clave, String mensajeEsperado) {
        LoginPage login = new LoginPage(pagina);
        login.abrir();
        login.iniciarSesion(usuario, clave);

        assertEquals(mensajeEsperado, login.mensajeDeError());
    }
}
