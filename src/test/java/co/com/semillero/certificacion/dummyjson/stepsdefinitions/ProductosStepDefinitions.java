package co.com.semillero.certificacion.dummyjson.stepsdefinitions;

import co.com.semillero.certificacion.dummyjson.exceptions.CuerpoDeRespuestaInesperado;
import co.com.semillero.certificacion.dummyjson.models.Producto;
import co.com.semillero.certificacion.dummyjson.models.ProductoEsperado;
import co.com.semillero.certificacion.dummyjson.questions.CuerpoDeLaRespuesta;
import co.com.semillero.certificacion.dummyjson.tasks.BuscarProductos;
import co.com.semillero.certificacion.dummyjson.tasks.ConsultarProducto;
import co.com.semillero.certificacion.dummyjson.tasks.CrearProducto;
import co.com.semillero.certificacion.dummyjson.utils.LectorCsv;
import co.com.semillero.certificacion.dummyjson.utils.MemoriaDelActor;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;

/**
 * STEPS DE PRODUCTOS (features/productos/consulta_productos.feature y creacion_productos.feature).
 */
public class ProductosStepDefinitions {

    /** Ruta del archivo CSV de productos dentro de src/test/resources. */
    private static final String ARCHIVO_PRODUCTOS = "data/productos.csv";

    /**
     * NIVEL 2 DE DATA DRIVEN: busca en el CSV la fila del caso (columna "caso") y la guarda en la memoria del actor.
     * El feature solo conoce el id del caso; los datos reales viven en el archivo.
     */
    @Dado("que el analista carga el caso {string} del archivo de productos")
    public void cargarCasoDelArchivo(String caso) {
        ProductoEsperado esperado = ProductoEsperado.desdeFila(LectorCsv.buscarFila(ARCHIVO_PRODUCTOS, "caso", caso));
        theActorInTheSpotlight().remember(MemoriaDelActor.PRODUCTO_ESPERADO, esperado);
    }

    /** Consulta el producto cuyo id está en la fila del CSV que se cargó en el paso anterior. */
    @Cuando("consulta el producto de ese caso")
    public void consultarProductoDelCaso() {
        Actor actor = theActorInTheSpotlight();
        ProductoEsperado esperado = actor.recall(MemoriaDelActor.PRODUCTO_ESPERADO);
        actor.attemptsTo(ConsultarProducto.conId(esperado.getId()));
    }

    /** Consulta un producto con el id escrito en el feature (se usa para el caso 404). */
    @Cuando("el analista consulta el producto con id {string}")
    public void consultarProductoConId(String id) {
        theActorInTheSpotlight().attemptsTo(ConsultarProducto.conId(id));
    }

    /** Busca productos por texto (columna texto de los Ejemplos). */
    @Cuando("el analista busca productos con el texto {string}")
    public void buscarProductos(String texto) {
        theActorInTheSpotlight().attemptsTo(BuscarProductos.conElTexto(texto));
    }

    /**
     * Compara la respuesta (convertida al modelo Producto) con los valores del CSV.
     * hasProperty("titulo", ...) lee el getter getTitulo() del modelo.
     */
    @Y("el producto devuelto tiene el título, la categoría y el precio del archivo")
    public void validarProductoContraArchivo() {
        Actor actor = theActorInTheSpotlight();
        ProductoEsperado esperado = actor.recall(MemoriaDelActor.PRODUCTO_ESPERADO);
        actor.should(
                seeThat(CuerpoDeLaRespuesta.comoModelo(Producto.class), allOf(
                        hasProperty("id", equalTo(esperado.getId())),
                        hasProperty("titulo", equalTo(esperado.getTitulo())),
                        hasProperty("categoria", equalTo(esperado.getCategoria())),
                        hasProperty("precio", equalTo(esperado.getPrecio()))))
                        .orComplainWith(CuerpoDeRespuestaInesperado.class, CuerpoDeRespuestaInesperado.MENSAJE_MODELO)
        );
    }

    /**
     * NIVEL 3 DE DATA DRIVEN: Cucumber convierte la tabla en un Producto usando ConversionDeDatos (@DataTableType).
     * Por eso el parámetro ya es un Producto y no un DataTable.
     */
    @Cuando("el analista crea un producto con los datos:")
    public void crearProducto(Producto producto) {
        theActorInTheSpotlight().attemptsTo(CrearProducto.conLosDatos(producto));
    }

    /**
     * Valida el eco de la API: el cuerpo, convertido a Producto, debe ser igual (equals) al que se envió
     * y debe traer un id asignado.
     */
    @Y("la respuesta devuelve el producto creado con un id y los mismos datos enviados")
    public void validarProductoCreado() {
        Actor actor = theActorInTheSpotlight();
        Producto enviado = actor.recall(MemoriaDelActor.PRODUCTO_ENVIADO);
        actor.should(
                seeThat(CuerpoDeLaRespuesta.comoModelo(Producto.class), allOf(
                        hasProperty("id", notNullValue()),
                        equalTo(enviado)))
                        .orComplainWith(CuerpoDeRespuestaInesperado.class, CuerpoDeRespuestaInesperado.MENSAJE_MODELO)
        );
    }
}
