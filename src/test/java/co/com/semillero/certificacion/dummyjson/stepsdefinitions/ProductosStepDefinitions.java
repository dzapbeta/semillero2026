package co.com.semillero.certificacion.dummyjson.stepsdefinitions;

import co.com.semillero.certificacion.dummyjson.exceptions.RespuestaInesperada;
import co.com.semillero.certificacion.dummyjson.questions.CampoDeLaRespuesta;
import co.com.semillero.certificacion.dummyjson.tasks.ConsultarProducto;
import co.com.semillero.certificacion.dummyjson.utils.LectorCsv;
import co.com.semillero.certificacion.dummyjson.utils.Rutas;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.hamcrest.Matchers.equalTo;

// Traduce las frases de productos.feature a acciones del actor.
public class ProductosStepDefinitions {

    // Nombre del actor. Es el que aparece en el reporte.
    private static final String ACTOR = "el analista";

    // "Cuando el analista consulta el producto con id ..."
    @Cuando("el analista consulta el producto con id {string}")
    public void consultarProducto(String id) {
        theActorCalled(ACTOR).attemptsTo(ConsultarProducto.conId(id));
    }

    // "Y el título del producto debe ser el que dice el archivo para el id ..."
    // Primero busca en el CSV el título esperado y después lo compara con el campo "title" de la respuesta.
    @Entonces("el título del producto debe ser el que dice el archivo para el id {string}")
    public void validarTituloDesdeArchivo(String id) {
        String tituloEsperado = LectorCsv.buscarValor(Rutas.ARCHIVO_PRODUCTOS, id);
        theActorCalled(ACTOR).should(
                seeThat(CampoDeLaRespuesta.llamado("title"), equalTo(tituloEsperado))
                        .orComplainWith(RespuestaInesperada.class, RespuestaInesperada.CAMPO_DIFERENTE)
        );
    }
}
