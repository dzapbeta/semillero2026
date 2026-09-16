package co.com.semillero.certificacion.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Capa USER INTERFACES: localizadores de la página de inicio de sesión.
 * Aquí SOLO se dice DÓNDE están los elementos; no se hace clic ni se escribe nada.
 */
public final class PaginaDeLogin {

    /** Campo de texto del nombre de usuario. Localizador por id (el más estable). */
    public static final Target CAMPO_USUARIO = Target.the("campo usuario")
            .located(By.id("user-name"));

    /** Campo de texto de la contraseña. Localizador por id. */
    public static final Target CAMPO_CLAVE = Target.the("campo contraseña")
            .located(By.id("password"));

    /** Botón "Login". Localizador por id. */
    public static final Target BOTON_INGRESAR = Target.the("botón ingresar")
            .located(By.id("login-button"));

    /** Mensaje rojo de error (por ejemplo, usuario bloqueado). Localizador CSS por atributo data-test. */
    public static final Target MENSAJE_DE_ERROR = Target.the("mensaje de error del login")
            .locatedBy("[data-test='error']");

    /** Constructor privado: la clase solo agrupa constantes, no se instancia. */
    private PaginaDeLogin() {
    }
}
