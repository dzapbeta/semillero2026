package co.com.semillero.certificacion.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Capa USER INTERFACES: localizadores de la página de productos (inventario).
 */
public final class PaginaDeInventario {

    /** Título de la sección (muestra "Products"). Localizador CSS por clase. */
    public static final Target TITULO_DE_LA_SECCION = Target.the("título de la sección")
            .locatedBy(".title");

    /** Lista desplegable para ordenar productos. Localizador CSS por atributo data-test. */
    public static final Target LISTA_DE_ORDENAMIENTO = Target.the("lista de ordenamiento")
            .locatedBy("[data-test='product-sort-container']");

    /** Nombres de todos los productos, en el orden en que se ven. Localizador CSS por clase. */
    public static final Target NOMBRES_DE_PRODUCTOS = Target.the("nombres de los productos")
            .locatedBy(".inventory_item_name");

    /**
     * Botón "Add to cart" de UN producto, buscado por su nombre visible.
     * Localizador XPath DINÁMICO: {0} se reemplaza con .of("nombre del producto").
     */
    public static final Target BOTON_AGREGAR_PRODUCTO = Target.the("botón agregar de {0}")
            .locatedBy("//div[@class='inventory_item'][.//div[text()='{0}']]"
                    + "//button[text()='Add to cart']");

    /** Número rojo sobre el ícono del carrito (cantidad de productos). */
    public static final Target CANTIDAD_EN_CARRITO = Target.the("cantidad en el carrito")
            .locatedBy(".shopping_cart_badge");

    /** Ícono del carrito que lleva a la página del carrito. */
    public static final Target ICONO_CARRITO = Target.the("ícono del carrito")
            .locatedBy(".shopping_cart_link");

    /** Constructor privado: la clase solo agrupa constantes. */
    private PaginaDeInventario() {
    }
}
