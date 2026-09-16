package co.com.semillero.playwright.pages;

import co.com.semillero.playwright.components.EncabezadoComponent;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

/**
 * Page Object del catálogo de productos (inventory.html), a donde se llega tras un login exitoso.
 * <p>
 * <b>Herencia</b> de {@link PaginaBase} y <b>composición</b> con {@link EncabezadoComponent}.
 */
public class InventarioPage extends PaginaBase {

    /** Título de la sección ("Products"), data-test="title". Sirve para confirmar que el login funcionó. */
    private final Locator titulo;

    /** Todas las tarjetas de producto del catálogo (data-test="inventory-item"). */
    private final Locator tarjetasDeProducto;

    /** Encabezado compartido (carrito y contador). Composición: la página TIENE un encabezado. */
    private final EncabezadoComponent encabezado;

    /**
     * Constructor: define localizadores y crea el componente del encabezado.
     *
     * @param pagina pestaña del navegador
     */
    public InventarioPage(Page pagina) {
        super(pagina);
        this.titulo = pagina.getByTestId("title");
        this.tarjetasDeProducto = pagina.getByTestId("inventory-item");
        this.encabezado = new EncabezadoComponent(pagina);
    }

    /**
     * Espera a que el catálogo esté visible (confirma que el login llevó a esta página).
     *
     * @return esta misma página, ya cargada
     */
    public InventarioPage esperarQueCargue() {
        esperarVisible(titulo);
        return this;
    }

    /**
     * Agrega un producto al carrito buscándolo por su nombre visible.
     * Primero filtra la tarjeta que contiene el nombre y, DENTRO de ella, busca el botón
     * "Add to cart". Así el localizador es único aunque haya seis botones iguales en la página.
     *
     * @param nombreProducto nombre exacto que se ve en el catálogo (ej. "Sauce Labs Backpack")
     * @return esta misma página, para poder agregar varios productos seguidos
     */
    public InventarioPage agregarAlCarrito(String nombreProducto) {
        Locator tarjeta = tarjetasDeProducto.filter(new Locator.FilterOptions().setHasText(nombreProducto));
        hacerClic(tarjeta.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart")));
        return this;
    }

    /**
     * Expone el título para validarlo con una aserción web-first.
     *
     * @return localizador del título de la sección
     */
    public Locator tituloDeLaSeccion() {
        return titulo;
    }

    /**
     * Da acceso al encabezado (carrito) de esta página.
     *
     * @return el componente del encabezado
     */
    public EncabezadoComponent encabezado() {
        return encabezado;
    }
}
