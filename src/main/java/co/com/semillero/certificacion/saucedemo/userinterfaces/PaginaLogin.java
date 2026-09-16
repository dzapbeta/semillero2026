package co.com.semillero.certificacion.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

// Aquí están los elementos de la página de inicio de sesión. Solo dice dónde están, no hace clics.
public class PaginaLogin {

    // Campo donde se escribe el usuario. En la página tiene id="user-name".
    public static final Target CAMPO_USUARIO = Target.the("campo usuario")
            .located(By.id("user-name"));

    // Campo donde se escribe la clave.
    public static final Target CAMPO_CLAVE = Target.the("campo clave")
            .located(By.id("password"));

    // Botón Login.
    public static final Target BOTON_INGRESAR = Target.the("botón ingresar")
            .located(By.id("login-button"));

    // Texto rojo que aparece cuando no se puede entrar.
    public static final Target MENSAJE_ERROR = Target.the("mensaje de error")
            .located(By.cssSelector("[data-test='error']"));

    // Constructor privado: la clase solo agrupa los elementos, no se crean objetos de ella.
    private PaginaLogin() {
    }
}
