package co.com.semillero.playwright.tests;

import co.com.semillero.playwright.models.Usuario;
import co.com.semillero.playwright.pages.InventarioPage;
import co.com.semillero.playwright.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Pruebas de inicio de sesión en saucedemo.com (mismos escenarios que la clase 2 con Screenplay).
 * <p>
 * Fíjate que la prueba NO tiene selectores ni {@code Thread.sleep}: solo usa métodos de las páginas
 * y aserciones web-first que reintentan solas hasta que se cumplen (o vence el tiempo).
 */
@Tag("login")
@DisplayName("Inicio de sesión en SauceDemo")
class LoginTest extends BaseTest {

    /**
     * Escenario 1: un usuario válido inicia sesión y ve el catálogo de productos.
     */
    @Test
    @DisplayName("Login exitoso con usuario estándar")
    void loginExitoso() {
        InventarioPage inventario = new LoginPage(pagina)
                .abrir()
                .iniciarSesionCon(Usuario.estandar());

        assertThat(inventario.tituloDeLaSeccion()).hasText("Products");
        assertThat(pagina).hasURL(java.util.regex.Pattern.compile(".*/inventory\\.html"));
    }

    /**
     * Escenario 2: un usuario bloqueado no puede entrar y ve el mensaje de error.
     */
    @Test
    @DisplayName("Login con usuario bloqueado muestra mensaje de error")
    void loginConUsuarioBloqueado() {
        LoginPage login = new LoginPage(pagina)
                .abrir()
                .intentarIniciarSesionCon(Usuario.bloqueado());

        assertThat(login.mensajeDeError()).hasText("Epic sadface: Sorry, this user has been locked out.");
    }

    /**
     * Data driven con {@code @ValueSource}: varios usuarios válidos deben llegar al catálogo.
     * performance_glitch_user tarda varios segundos en entrar: el auto-waiting lo resuelve sin sleeps.
     *
     * @param nombreUsuario usuario válido de saucedemo (JUnit ejecuta la prueba una vez por valor)
     */
    @ParameterizedTest(name = "usuario {0}")
    @ValueSource(strings = {"standard_user", "problem_user", "performance_glitch_user"})
    @DisplayName("Login exitoso con distintos usuarios válidos")
    void loginExitosoConUsuariosValidos(String nombreUsuario) {
        InventarioPage inventario = new LoginPage(pagina)
                .abrir()
                .iniciarSesionCon(new Usuario(nombreUsuario, "secret_sauce"));

        assertThat(inventario.tituloDeLaSeccion()).hasText("Products");
    }

    /**
     * Data driven con {@code @CsvFileSource}: cada fila de {@code datos/login-invalido.csv} es un caso
     * (usuario, clave, mensaje esperado). Agregar un caso = agregar una fila, sin tocar Java.
     *
     * @param usuario         nombre de usuario de la fila (vacío = campo sin diligenciar)
     * @param clave           clave de la fila (vacía = campo sin diligenciar)
     * @param mensajeEsperado mensaje de error que debe mostrar la aplicación
     */
    @ParameterizedTest(name = "[{index}] usuario {0} → {2}")
    @CsvFileSource(resources = "/datos/login-invalido.csv", numLinesToSkip = 1)
    @DisplayName("Login inválido muestra el mensaje correcto")
    void loginInvalidoMuestraMensaje(String usuario, String clave, String mensajeEsperado) {
        LoginPage login = new LoginPage(pagina)
                .abrir()
                .intentarIniciarSesionCon(new Usuario(usuario, clave));

        assertThat(login.mensajeDeError()).hasText(mensajeEsperado);
    }
}
