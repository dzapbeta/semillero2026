package co.com.semillero.certificacion.dummyjson.tasks;

import co.com.semillero.certificacion.dummyjson.interactions.ConsultarConToken;
import co.com.semillero.certificacion.dummyjson.utils.Endpoints;
import co.com.semillero.certificacion.dummyjson.utils.MemoriaDelActor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * TAREA: consultar el perfil del usuario autenticado (GET /auth/me).
 * Usa la interacción propia ConsultarConToken. Tiene dos variantes: con el token guardado o sin token (caso negativo).
 */
public class ConsultarPerfil implements Task {

    /** true = envía el token guardado; false = lo olvida antes de consultar para probar el error 401. */
    private final boolean conToken;

    /** Constructor público usado por Serenity al instrumentar la tarea. */
    public ConsultarPerfil(boolean conToken) {
        this.conToken = conToken;
    }

    /** Variante positiva: consulta el perfil con el token que se guardó al iniciar sesión. */
    public static ConsultarPerfil conSuToken() {
        return instrumented(ConsultarPerfil.class, true);
    }

    /** Variante negativa: consulta el perfil sin enviar token. */
    public static ConsultarPerfil sinToken() {
        return instrumented(ConsultarPerfil.class, false);
    }

    /** Pasos de la tarea: (si aplica) borrar el token de la memoria y luego consultar el recurso protegido. */
    @Override
    @Step("{0} consulta su perfil autenticado")
    public <T extends Actor> void performAs(T actor) {
        if (!conToken) {
            actor.forget(MemoriaDelActor.TOKEN_DE_ACCESO);
        }
        actor.attemptsTo(ConsultarConToken.elRecurso(Endpoints.PERFIL_AUTENTICADO));
    }
}
