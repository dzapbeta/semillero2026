package co.com.semillero.certificacion.saucedemo.interactions;

import co.com.semillero.certificacion.saucedemo.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Capa INTERACTIONS: espera explícita hasta que un elemento sea visible.
 * Evita fallas por intentar usar un elemento que la página todavía no ha pintado.
 * POO - POLIMORFISMO: implementa la interfaz Interaction.
 */
public class EsperarVisible implements Interaction {

    /** Elemento (localizador de la capa userinterfaces) que se espera. */
    private final Target elemento;

    /** Constructor público: guarda el elemento a esperar. */
    public EsperarVisible(Target elemento) {
        this.elemento = elemento;
    }

    /** Espera como máximo Constantes.SEGUNDOS_DE_ESPERA segundos a que el elemento se vea. */
    @Override
    @Step("{0} espera a que se vea #elemento")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(elemento, isVisible()).forNoMoreThan(Constantes.SEGUNDOS_DE_ESPERA).seconds()
        );
    }

    /** Método de fábrica legible: EsperarVisible.elElemento(PaginaDeLogin.CAMPO_USUARIO). */
    public static EsperarVisible elElemento(Target elemento) {
        return instrumented(EsperarVisible.class, elemento);
    }
}
