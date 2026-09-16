package co.com.semillero.certificacion.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Capa USER INTERFACES: localizadores de las pantallas del checkout
 * (datos de envío, resumen y confirmación de la orden).
 */
public final class PaginaDeCheckout {

    /** Campo "First Name". Localizador por id. */
    public static final Target CAMPO_NOMBRE = Target.the("campo nombre")
            .located(By.id("first-name"));

    /** Campo "Last Name". Localizador por id. */
    public static final Target CAMPO_APELLIDO = Target.the("campo apellido")
            .located(By.id("last-name"));

    /** Campo "Zip/Postal Code". Localizador por id. */
    public static final Target CAMPO_CODIGO_POSTAL = Target.the("campo código postal")
            .located(By.id("postal-code"));

    /** Botón "Continue" que pasa al resumen de la compra. */
    public static final Target BOTON_CONTINUAR = Target.the("botón continuar")
            .located(By.id("continue"));

    /** Botón "Finish" que confirma la orden. */
    public static final Target BOTON_FINALIZAR = Target.the("botón finalizar")
            .located(By.id("finish"));

    /** Título de confirmación ("Thank you for your order!"). Localizador CSS por clase. */
    public static final Target MENSAJE_DE_CONFIRMACION = Target.the("mensaje de confirmación")
            .locatedBy(".complete-header");

    /** Constructor privado: la clase solo agrupa constantes. */
    private PaginaDeCheckout() {
    }
}
