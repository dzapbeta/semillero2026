package co.com.semillero.certificacion.dummyjson.stepsdefinitions;

import co.com.semillero.certificacion.dummyjson.utils.Rutas;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

// Código que Cucumber ejecuta solo, antes de cada escenario.
public class Hooks {

    // Prepara el escenario: todo actor que aparezca tendrá la habilidad de llamar a la API.
    // En la rama web la habilidad era BrowseTheWeb (navegador); aquí es CallAnApi.
    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(Cast.whereEveryoneCan(CallAnApi.at(Rutas.URL_BASE)));
    }
}
