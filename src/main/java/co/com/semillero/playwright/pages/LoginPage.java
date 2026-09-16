package co.com.semillero.playwright.pages;

import co.com.semillero.playwright.models.Usuario;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

/**
 * Page Object de la pantalla de inicio de sesión de saucedemo.com.
 * <p>
 * <b>Herencia:</b> extiende {@link PaginaBase}. <b>Encapsulamiento:</b> los localizadores son
 * privados; la prueba solo llama métodos de negocio ({@code iniciarSesionCon}) y nunca ve un selector.
 * Si mañana cambia el HTML, se corrige aquí y ninguna prueba se toca.
 */
public class LoginPage extends PaginaBase {

    /** Campo "Username", localizado por su texto de ayuda (placeholder), como lo ve el usuario. */
    private final Locator campoUsuario;

    /** Campo "Password", localizado por su atributo de pruebas data-test="password". */
    private final Locator campoClave;

    /** Botón "Login", localizado por su rol accesible (botón) y su nombre visible. */
    private final Locator botonIngresar;

    /** Mensaje de error rojo que aparece cuando el login falla (data-test="error"). */
    private final Locator mensajeError;

    /**
     * Constructor: recibe la pestaña y define los localizadores (todavía no busca nada en la página).
     *
     * @param pagina pestaña del navegador
     */
    public LoginPage(Page pagina) {
        super(pagina);
        this.campoUsuario = pagina.getByPlaceholder("Username");
        this.campoClave = pagina.getByTestId("password");
        this.botonIngresar = pagina.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        this.mensajeError = pagina.getByTestId("error");
    }

    /**
     * Abre la página de login (URL base + "/").
     *
     * @return esta misma página, para seguir encadenando
     */
    public LoginPage abrir() {
        navegar("/");
        return this;
    }

    /**
     * Inicia sesión con un usuario válido.
     *
     * @param usuario credenciales a usar
     * @return la página de inventario, que es a donde lleva un login exitoso
     */
    public InventarioPage iniciarSesionCon(Usuario usuario) {
        diligenciarYEnviar(usuario);
        return new InventarioPage(pagina).esperarQueCargue();
    }

    /**
     * Intenta iniciar sesión esperando que falle (usuario bloqueado, clave errada, campos vacíos).
     *
     * @param usuario credenciales a usar
     * @return esta misma página, porque un login fallido no sale del formulario
     */
    public LoginPage intentarIniciarSesionCon(Usuario usuario) {
        diligenciarYEnviar(usuario);
        return this;
    }

    /**
     * Expone el mensaje de error para validarlo con {@code assertThat(...).hasText(...)}.
     *
     * @return localizador del mensaje de error
     */
    public Locator mensajeDeError() {
        return mensajeError;
    }

    /**
     * Paso interno compartido por los dos métodos de login: escribe usuario y clave y pulsa "Login".
     * Es privado: detalle de implementación que las pruebas no necesitan conocer.
     *
     * @param usuario credenciales a escribir
     */
    private void diligenciarYEnviar(Usuario usuario) {
        escribir(campoUsuario, usuario.getNombreUsuario());
        escribir(campoClave, usuario.getClave());
        hacerClic(botonIngresar);
    }
}
