package co.com.semillero.certificacion.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

// Elementos de la página de productos, la que se ve después de iniciar sesión.
public class PaginaProductos {

    // Título de la página. Cuando el login sale bien dice "Products".
    public static final Target TITULO = Target.the("título de la página")
            .located(By.cssSelector("[data-test='title']"));

    // Constructor privado: la clase solo agrupa los elementos, no se crean objetos de ella.
    private PaginaProductos() {
    }
}
