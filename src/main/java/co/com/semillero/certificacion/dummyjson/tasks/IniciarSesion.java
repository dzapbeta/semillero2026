package co.com.semillero.certificacion.dummyjson.tasks;

import co.com.semillero.certificacion.dummyjson.models.CredencialesLogin;
import co.com.semillero.certificacion.dummyjson.utils.Endpoints;
import co.com.semillero.certificacion.dummyjson.utils.MemoriaDelActor;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * TAREA: iniciar sesión en la API (POST /auth/login).
 * Si la API responde 200, guarda el accessToken en la memoria del actor para usarlo después.
 *
 * POO - POLIMORFISMO: implementa Task. El actor la ejecuta con attemptsTo(...) igual que cualquier otra tarea.
 */
public class IniciarSesion implements Task {

    /** Código HTTP de éxito del login. */
    private static final int OK = 200;

    /** Credenciales que se envían en el cuerpo (modelo de request). */
    private final CredencialesLogin credenciales;

    /** Constructor público usado por Serenity al instrumentar la tarea. */
    public IniciarSesion(CredencialesLogin credenciales) {
        this.credenciales = credenciales;
    }

    /** Método de fábrica: se lee como frase en el step → IniciarSesion.con(usuario, clave). */
    public static IniciarSesion con(String usuario, String clave) {
        return instrumented(IniciarSesion.class, CredencialesLogin.de(usuario, clave));
    }

    /** Pasos de la tarea: enviar el POST con el JSON y, si fue exitoso, recordar el token. */
    @Override
    @Step("{0} inicia sesión con #credenciales")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Endpoints.INICIAR_SESION).with(peticion -> peticion
                        .contentType(ContentType.JSON)
                        .body(credenciales))
        );
        if (SerenityRest.lastResponse().statusCode() == OK) {
            actor.remember(MemoriaDelActor.TOKEN_DE_ACCESO,
                    SerenityRest.lastResponse().jsonPath().getString("accessToken"));
        }
    }
}
