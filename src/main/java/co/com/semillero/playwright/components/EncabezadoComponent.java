package co.com.semillero.playwright.components;

import co.com.semillero.playwright.pages.CarritoPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Componente del encabezado de saucedemo (ícono del carrito y su contador).
 * <p>
 * <b>Composición en lugar de herencia:</b> el encabezado aparece en el inventario y en el carrito.
 * En vez de copiar sus localizadores en cada página (o inventar una herencia forzada), cada página
 * "tiene un" {@code EncabezadoComponent}. Regla práctica: herencia para "es un" (LoginPage ES una
 * página), composición para "tiene un" (InventarioPage TIENE un encabezado).
 */
public class EncabezadoComponent {

    /** Pestaña del navegador; se necesita para crear la siguiente página (CarritoPage). */
    private final Page pagina;

    /** Enlace del ícono del carrito (atributo data-test="shopping-cart-link"). Privado: encapsulado. */
    private final Locator enlaceCarrito;

    /** Globo con la cantidad de productos en el carrito (data-test="shopping-cart-badge"). */
    private final Locator contadorCarrito;

    /**
     * Constructor: define los localizadores. Crear un Locator NO busca el elemento todavía;
     * Playwright lo busca en el momento de usarlo (por eso nunca queda "viejo").
     *
     * @param pagina pestaña del navegador
     */
    public EncabezadoComponent(Page pagina) {
        this.pagina = pagina;
        this.enlaceCarrito = pagina.getByTestId("shopping-cart-link");
        this.contadorCarrito = pagina.getByTestId("shopping-cart-badge");
    }

    /**
     * Abre el carrito de compras.
     *
     * @return la página del carrito (encadenamiento fluido)
     */
    public CarritoPage irAlCarrito() {
        enlaceCarrito.click();
        return new CarritoPage(pagina).esperarQueCargue();
    }

    /**
     * Expone el contador del carrito SOLO para validarlo con aserciones web-first
     * ({@code assertThat(...).hasText("1")}). La prueba no conoce el selector.
     *
     * @return localizador del contador del carrito
     */
    public Locator contadorDelCarrito() {
        return contadorCarrito;
    }
}
