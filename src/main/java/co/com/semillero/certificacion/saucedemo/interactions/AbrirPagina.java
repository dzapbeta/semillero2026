package co.com.semillero.certificacion.saucedemo.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Capa INTERACTIONS: acción técnica de bajo nivel que abre una URL en el navegador.
 * POO - POLIMORFISMO: implementa la interfaz Interaction; Serenity la ejecuta
 * llamando a performAs(...) sin saber qué clase concreta es.
 */
public class AbrirPagina implements Interaction {

    /** Dirección web que se va a abrir. */
    private final String url;

    /** Constructor: guarda la URL. Debe ser público para que Serenity pueda "instrumentar" la clase. */
    public AbrirPagina(String url) {
        this.url = url;
    }

    /**
     * Lo que hace la interacción: usa la habilidad BrowseTheWeb del actor para abrir la URL.
     * El texto de @Step es lo que aparece en el reporte ({0} = nombre del actor, #url = atributo).
     */
    @Override
    @Step("{0} abre la página #url")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).openUrl(url);
    }

    /** Método de fábrica legible: AbrirPagina.enLaUrl("https://..."). */
    public static AbrirPagina enLaUrl(String url) {
        return instrumented(AbrirPagina.class, url);
    }
}
