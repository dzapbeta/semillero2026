package co.com.semillero.certificacion.saucedemo.interactions;

import co.com.semillero.certificacion.saucedemo.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.Tasks.instrumented;

// Interacción propia: una sola acción pequeña con el navegador, abrir la tienda.
public class AbrirLaPagina implements Interaction {

    // Lo que hace el actor. El texto de @Step es el que aparece en el reporte.
    @Step("{0} abre la página de Sauce Demo")
    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).openUrl(Constantes.URL_SAUCE_DEMO);
    }

    // Forma cómoda de crear la interacción: AbrirLaPagina.deSauceDemo().
    public static AbrirLaPagina deSauceDemo() {
        return instrumented(AbrirLaPagina.class);
    }
}
