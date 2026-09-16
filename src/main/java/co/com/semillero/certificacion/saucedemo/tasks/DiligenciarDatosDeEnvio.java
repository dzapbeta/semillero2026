package co.com.semillero.certificacion.saucedemo.tasks;

import co.com.semillero.certificacion.saucedemo.models.DatosDeEnvio;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeCheckout.BOTON_CONTINUAR;
import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeCheckout.CAMPO_APELLIDO;
import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeCheckout.CAMPO_CODIGO_POSTAL;
import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeCheckout.CAMPO_NOMBRE;
import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDeInventario.ICONO_CARRITO;
import static co.com.semillero.certificacion.saucedemo.userinterfaces.PaginaDelCarrito.BOTON_PAGAR;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Capa TASKS: tarea de negocio "diligenciar los datos de envío".
 * Va al carrito, inicia el checkout, llena el formulario y continúa al resumen.
 * POO - POLIMORFISMO: implementa la interfaz Task.
 */
public class DiligenciarDatosDeEnvio implements Task {

    /** Datos de envío (modelo) que se escriben en el formulario. */
    private final DatosDeEnvio datos;

    /** Constructor público: guarda los datos de envío. */
    public DiligenciarDatosDeEnvio(DatosDeEnvio datos) {
        this.datos = datos;
    }

    /** Secuencia de interacciones del checkout (paso 1 de 2 en Sauce Demo). */
    @Override
    @Step("{0} diligencia los datos de envío de #datos")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(ICONO_CARRITO),
                Click.on(BOTON_PAGAR),
                Enter.theValue(datos.getNombre()).into(CAMPO_NOMBRE),
                Enter.theValue(datos.getApellido()).into(CAMPO_APELLIDO),
                Enter.theValue(datos.getCodigoPostal()).into(CAMPO_CODIGO_POSTAL),
                Click.on(BOTON_CONTINUAR)
        );
    }

    /** Método de fábrica legible: DiligenciarDatosDeEnvio.con(datos). */
    public static DiligenciarDatosDeEnvio con(DatosDeEnvio datos) {
        return instrumented(DiligenciarDatosDeEnvio.class, datos);
    }
}
