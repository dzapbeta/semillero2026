package co.com.semillero.certificacion.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Capa USER INTERFACES: localizadores de la página del carrito de compras.
 */
public final class PaginaDelCarrito {

    /** Botón "Checkout" que inicia el proceso de pago. Localizador por id. */
    public static final Target BOTON_PAGAR = Target.the("botón checkout")
            .located(By.id("checkout"));

    /** Constructor privado: la clase solo agrupa constantes. */
    private PaginaDelCarrito() {
    }
}
