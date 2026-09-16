package co.com.semillero.playwright.pages;

import co.com.semillero.playwright.models.DatosDeEnvio;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

/**
 * Page Object del proceso de pago: formulario de datos (paso 1), resumen (paso 2) y confirmación.
 * <p>
 * Se modela como una sola página para mantener el ejemplo corto; en un proyecto grande cada paso
 * podría ser su propio Page Object. <b>Herencia</b> de {@link PaginaBase}.
 */
public class CheckoutPage extends PaginaBase {

    /** Campo "First Name" (data-test="firstName"). */
    private final Locator campoNombre;

    /** Campo "Last Name" (data-test="lastName"). */
    private final Locator campoApellido;

    /** Campo "Zip/Postal Code" (data-test="postalCode"). */
    private final Locator campoCodigoPostal;

    /** Botón "Continue" del paso 1 (data-test="continue"). */
    private final Locator botonContinuar;

    /** Botón "Finish" del paso 2, localizado por rol y nombre visible. */
    private final Locator botonFinalizar;

    /** Etiqueta con el total a pagar en el resumen, ej. "Total: $32.39" (data-test="total-label"). */
    private final Locator etiquetaTotal;

    /** Encabezado de confirmación "Thank you for your order!" (data-test="complete-header"). */
    private final Locator mensajeConfirmacion;

    /**
     * Constructor: define los localizadores de los tres momentos del checkout.
     *
     * @param pagina pestaña del navegador
     */
    public CheckoutPage(Page pagina) {
        super(pagina);
        this.campoNombre = pagina.getByTestId("firstName");
        this.campoApellido = pagina.getByTestId("lastName");
        this.campoCodigoPostal = pagina.getByTestId("postalCode");
        this.botonContinuar = pagina.getByTestId("continue");
        this.botonFinalizar = pagina.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Finish"));
        this.etiquetaTotal = pagina.getByTestId("total-label");
        this.mensajeConfirmacion = pagina.getByTestId("complete-header");
    }

    /**
     * Diligencia el formulario de envío y pulsa "Continue" para ir al resumen de la orden.
     *
     * @param datos nombre, apellido y código postal
     * @return esta misma página (ahora mostrando el resumen)
     */
    public CheckoutPage diligenciarDatosDeEnvio(DatosDeEnvio datos) {
        escribir(campoNombre, datos.getNombre());
        escribir(campoApellido, datos.getApellido());
        escribir(campoCodigoPostal, datos.getCodigoPostal());
        hacerClic(botonContinuar);
        return this;
    }

    /**
     * Lee el total de la orden que se muestra en el resumen (paso 2).
     *
     * @return el texto del total, por ejemplo "Total: $32.39"
     */
    public String totalDeLaOrden() {
        return obtenerTexto(etiquetaTotal);
    }

    /**
     * Pulsa "Finish" en el resumen para confirmar la compra.
     *
     * @return esta misma página (ahora mostrando la confirmación)
     */
    public CheckoutPage finalizarCompra() {
        hacerClic(botonFinalizar);
        return this;
    }

    /**
     * Expone el mensaje de confirmación para validarlo con una aserción web-first.
     *
     * @return localizador del encabezado de confirmación
     */
    public Locator mensajeDeConfirmacion() {
        return mensajeConfirmacion;
    }
}
