package co.com.semillero.certificacion.saucedemo.stepsdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * HOOKS: código que Cucumber ejecuta automáticamente antes (o después) de cada escenario.
 */
public class Hooks {

    /**
     * @Before: se ejecuta ANTES de cada escenario.
     * Prepara el "escenario de teatro" (Stage) con un OnlineCast: todo actor que se cree
     * recibe automáticamente la habilidad BrowseTheWeb (usar el navegador).
     */
    @Before
    public void prepararElEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }
}
