package co.com.semillero.certificacion.dummyjson.tasks;

import co.com.semillero.certificacion.dummyjson.models.Credenciales;
import co.com.semillero.certificacion.dummyjson.utils.Rutas;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

// Tarea: iniciar sesión en la API enviando usuario y clave con un POST.
public class IniciarSesion implements Task {

    // Usuario y clave que se van a enviar.
    private final Credenciales credenciales;

    // Serenity usa este constructor cuando crea la tarea con instrumented(...).
    public IniciarSesion(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

    // Forma cómoda de crear la tarea desde el step: IniciarSesion.con("emilys", "emilyspass")
    public static IniciarSesion con(String usuario, String clave) {
        return instrumented(IniciarSesion.class, new Credenciales(usuario, clave));
    }

    // Lo que hace el actor: un POST a /auth/login con el cuerpo en formato JSON.
    @Override
    @Step("{0} inicia sesión en la API")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Rutas.INICIAR_SESION)
                        .with(peticion -> peticion.contentType(ContentType.JSON).body(credenciales))
        );
    }
}
