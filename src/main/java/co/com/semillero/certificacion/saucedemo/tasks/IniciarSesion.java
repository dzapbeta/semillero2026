package co.com.semillero.certificacion.saucedemo.tasks;

import co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaLogin;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

// Tarea: lo que el usuario quiere lograr (entrar a la tienda), armado con varias acciones pequeñas.
public class IniciarSesion implements Task {

    // Usuario que se va a escribir en el formulario.
    private final String usuario;

    // Clave que se va a escribir en el formulario.
    private final String clave;

    // Constructor: guarda el usuario y la clave para usarlos en performAs.
    public IniciarSesion(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    // Pasos de la tarea: escribir usuario, escribir clave y dar clic en ingresar.
    @Step("{0} inicia sesión con el usuario #usuario")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(usuario).into(PaginaLogin.CAMPO_USUARIO),
                Enter.theValue(clave).into(PaginaLogin.CAMPO_CLAVE),
                Click.on(PaginaLogin.BOTON_INGRESAR)
        );
    }

    // Forma cómoda de crear la tarea: IniciarSesion.con("standard_user", "secret_sauce").
    public static IniciarSesion con(String usuario, String clave) {
        return instrumented(IniciarSesion.class, usuario, clave);
    }
}
