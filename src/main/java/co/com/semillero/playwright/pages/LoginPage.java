package co.com.semillero.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

// Page Object de la pantalla de inicio de sesión de saucedemo.com.
// Las pruebas no ven los localizadores: solo llaman abrir(), iniciarSesion() y mensajeDeError().
public class LoginPage extends PaginaBase {

    // Dirección de la pantalla de login.
    private static final String DIRECCION = "https://www.saucedemo.com/";

    // Campo donde se escribe el usuario. Lo buscamos por el texto gris que muestra adentro.
    private final Locator campoUsuario;

    // Campo donde se escribe la clave, también por su texto gris.
    private final Locator campoClave;

    // Botón "Login". Lo buscamos como lo ve una persona: un botón que dice Login.
    private final Locator botonLogin;

    // Mensaje rojo que sale cuando el login falla. Lo buscamos por su atributo data-test.
    private final Locator mensajeError;

    // Recibe la pestaña, se la pasa a PaginaBase y prepara los localizadores.
    // Preparar un localizador no busca nada todavía: Playwright lo busca cuando lo usas.
    public LoginPage(Page pagina) {
        super(pagina);
        campoUsuario = pagina.getByPlaceholder("Username");
        campoClave = pagina.getByPlaceholder("Password");
        botonLogin = pagina.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        mensajeError = pagina.locator("[data-test='error']");
    }

    // Abre la pantalla de login.
    public void abrir() {
        navegarA(DIRECCION);
    }

    // Escribe el usuario y la clave y pulsa Login.
    public void iniciarSesion(String usuario, String clave) {
        campoUsuario.fill(usuario);
        campoClave.fill(clave);
        botonLogin.click();
    }

    // Devuelve el texto del mensaje de error para que la prueba lo compare.
    public String mensajeDeError() {
        return mensajeError.textContent();
    }
}
