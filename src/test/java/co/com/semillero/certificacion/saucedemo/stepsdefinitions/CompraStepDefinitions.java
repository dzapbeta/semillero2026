package co.com.semillero.certificacion.saucedemo.stepsdefinitions;

import co.com.semillero.certificacion.saucedemo.exceptions.ErrorEnLaCompra;
import co.com.semillero.certificacion.saucedemo.models.DatosDeEnvio;
import co.com.semillero.certificacion.saucedemo.questions.CantidadEnCarrito;
import co.com.semillero.certificacion.saucedemo.questions.MensajeDeConfirmacion;
import co.com.semillero.certificacion.saucedemo.tasks.AgregarProductoAlCarrito;
import co.com.semillero.certificacion.saucedemo.tasks.DiligenciarDatosDeEnvio;
import co.com.semillero.certificacion.saucedemo.tasks.FinalizarCompra;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/**
 * STEP DEFINITIONS de la compra: carrito, datos de envío y confirmación.
 * Los pasos "Dado que ... abre la tienda" e "inicia sesión" se reutilizan desde LoginStepDefinitions.
 */
public class CompraStepDefinitions {

    /** Frase: Cuando agrega el producto "Sauce Labs Backpack" al carrito */
    @Cuando("agrega el producto {string} al carrito")
    public void agregaElProductoAlCarrito(String nombreDelProducto) {
        theActorInTheSpotlight().attemptsTo(AgregarProductoAlCarrito.llamado(nombreDelProducto));
    }

    /**
     * Frase: Entonces el carrito debería mostrar 1 producto
     * {int} convierte el número del feature a entero; "producto(s)" acepta singular o plural.
     */
    @Entonces("el carrito debería mostrar {int} producto(s)")
    public void elCarritoDeberiaMostrar(int cantidadEsperada) {
        theActorInTheSpotlight().should(
                seeThat(CantidadEnCarrito.actual(), equalTo(cantidadEsperada))
                        .orComplainWith(ErrorEnLaCompra.class,
                                ErrorEnLaCompra.CANTIDAD_EN_CARRITO_INCORRECTA)
        );
    }

    /**
     * Frase: Y diligencia los datos de envío con nombre "Ana", apellido "Pérez" y código postal "050001"
     * Arma el modelo DatosDeEnvio y ejecuta la tarea del checkout.
     */
    @Y("diligencia los datos de envío con nombre {string}, apellido {string} y código postal {string}")
    public void diligenciaLosDatosDeEnvio(String nombre, String apellido, String codigoPostal) {
        theActorInTheSpotlight().attemptsTo(
                DiligenciarDatosDeEnvio.con(new DatosDeEnvio(nombre, apellido, codigoPostal))
        );
    }

    /** Frase: Y finaliza la compra */
    @Y("finaliza la compra")
    public void finalizaLaCompra() {
        theActorInTheSpotlight().attemptsTo(FinalizarCompra.confirmandoLaOrden());
    }

    /** Frase: Entonces debería ver el mensaje de confirmación "Thank you for your order!" */
    @Entonces("debería ver el mensaje de confirmación {string}")
    public void deberiaVerElMensajeDeConfirmacion(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat(MensajeDeConfirmacion.deLaCompra(), equalTo(mensajeEsperado))
                        .orComplainWith(ErrorEnLaCompra.class,
                                ErrorEnLaCompra.COMPRA_NO_CONFIRMADA)
        );
    }
}
