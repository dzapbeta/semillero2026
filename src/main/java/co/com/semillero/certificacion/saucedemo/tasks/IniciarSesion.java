package co.com.semillero.certificacion.saucedemo.tasks;

import co.com.semillero.certificacion.saucedemo.models.Usuario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeLogin.BOTON_INGRESAR;
import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeLogin.CAMPO_CLAVE;
import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeLogin.CAMPO_USUARIO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Capa TASKS: tarea de negocio "iniciar sesión".
 * Usa interacciones que ya trae Serenity (Enter, Click) y localizadores de PaginaDeLogin.
 * POO - POLIMORFISMO: implementa la interfaz Task.
 */
public class IniciarSesion implements Task {

    /** Usuario (modelo) con el que se inicia sesión. */
    private final Usuario usuario;

    /** Constructor público: Serenity lo necesita para crear la tarea con instrumented(...). */
    public IniciarSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    /** Escribe usuario y clave y hace clic en "Login". */
    @Override
    @Step("{0} inicia sesión con el usuario #usuario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(usuario.getNombreDeUsuario())
                        .into(CAMPO_USUARIO),
                Enter.theValue(usuario.getClave())
                        .into(CAMPO_CLAVE),
                Click.on(BOTON_INGRESAR)
        );
    }

    /** Método de fábrica legible: IniciarSesion.conCredenciales(usuario). */
    public static IniciarSesion conCredenciales(Usuario usuario) {
        return instrumented(IniciarSesion.class, usuario);
    }
}
