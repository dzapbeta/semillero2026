package co.com.semillero.certificacion.dummyjson.interactions;

import co.com.semillero.certificacion.dummyjson.utils.MemoriaDelActor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * INTERACCIÓN PROPIA: envía un GET agregando la cabecera Authorization: Bearer <token>.
 *
 * ¿Interaction o Task? Una Interaction es una acción de BAJO nivel, técnica y pequeña (equivale a "hacer clic" en Web).
 * Una Task es una acción de NEGOCIO que agrupa interacciones ("iniciar sesión").
 * El token NO se recibe por parámetro: se lee de la memoria del actor (lo guardó la tarea IniciarSesion).
 *
 * POO - POLIMORFISMO: implementa la interfaz Interaction; Serenity la ejecuta con attemptsTo(...) igual que a
 * cualquier otra Interaction o Task, sin saber cuál es la clase concreta.
 */
public class ConsultarConToken implements Interaction {

    /** Ruta del recurso a consultar (por ejemplo Endpoints.PERFIL_AUTENTICADO). */
    private final String recurso;

    /** Constructor público: Serenity lo usa al "instrumentar" la clase para poder mostrar el paso en el reporte. */
    public ConsultarConToken(String recurso) {
        this.recurso = recurso;
    }

    /** Método de fábrica legible: ConsultarConToken.elRecurso(Endpoints.PERFIL_AUTENTICADO). */
    public static ConsultarConToken elRecurso(String recurso) {
        return instrumented(ConsultarConToken.class, recurso);
    }

    /**
     * Lo que hace la interacción. @Step define el texto del paso en el reporte (#recurso se reemplaza por su valor).
     * Si el actor no tiene token guardado, se envía sin cabecera para que la API responda el error real.
     */
    @Override
    @Step("{0} consulta #recurso enviando su token de acceso")
    public <T extends Actor> void performAs(T actor) {
        String token = actor.recall(MemoriaDelActor.TOKEN_DE_ACCESO);
        actor.attemptsTo(
                Get.resource(recurso).with(peticion -> token == null
                        ? peticion
                        : peticion.header("Authorization", "Bearer " + token))
        );
    }
}
