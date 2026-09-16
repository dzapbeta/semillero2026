package co.com.semillero.certificacion.saucedemo.stepsdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

// Hooks: código que Cucumber ejecuta solo, antes o después de cada escenario.
public class Hooks {

    // Antes de cada escenario prepara el escenario de teatro. Con OnlineCast cada actor
    // que se cree ya sabe usar el navegador.
    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }
}
