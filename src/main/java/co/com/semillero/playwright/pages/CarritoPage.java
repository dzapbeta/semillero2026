package co.com.semillero.playwright.pages;

import co.com.semillero.playwright.components.EncabezadoComponent;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page Object del carrito de compras (cart.html).
 * <p>
 * <b>Herencia</b> de {@link PaginaBase} y <b>composición</b> con {@link EncabezadoComponent}.
 */
public class CarritoPage extends PaginaBase {

    /** Nombres de los productos que hay en el carrito (data-test="inventory-item-name"). */
    private final Locator nombresDeProductos;

    /** Botón "Checkout" que inicia el proceso de pago (data-test="checkout"). */
    private final Locator botonCheckout;

    /** Encabezado compartido con el inventario. */
    private final EncabezadoComponent encabezado;

    /**
     * Constructor: define los localizadores del carrito.
     *
     * @param pagina pestaña del navegador
     */
    public CarritoPage(Page pagina) {
        super(pagina);
        this.nombresDeProductos = pagina.getByTestId("inventory-item-name");
        this.botonCheckout = pagina.getByTestId("checkout");
        this.encabezado = new EncabezadoComponent(pagina);
    }

    /**
     * Espera a que el carrito esté visible (botón "Checkout"). Evita validar productos mientras el
     * navegador todavía muestra el inventario, donde también hay nombres de productos.
     *
     * @return esta misma página, ya cargada
     */
    public CarritoPage esperarQueCargue() {
        esperarVisible(botonCheckout);
        return this;
    }

    /**
     * Expone la lista de nombres para validarla (ej. {@code hasText("Sauce Labs Backpack")}).
     *
     * @return localizador de los nombres de productos en el carrito
     */
    public Locator productosEnElCarrito() {
        return nombresDeProductos;
    }

    /**
     * Pulsa "Checkout" para pasar al formulario de datos de envío.
     *
     * @return la página de checkout
     */
    public CheckoutPage irAlCheckout() {
        hacerClic(botonCheckout);
        return new CheckoutPage(pagina);
    }

    /**
     * Da acceso al encabezado (carrito y contador) de esta página.
     *
     * @return el componente del encabezado
     */
    public EncabezadoComponent encabezado() {
        return encabezado;
    }
}
