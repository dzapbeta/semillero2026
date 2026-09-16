package co.com.semillero.playwright.tests;

import co.com.semillero.playwright.models.DatosDeEnvio;
import co.com.semillero.playwright.models.Usuario;
import co.com.semillero.playwright.pages.CarritoPage;
import co.com.semillero.playwright.pages.CheckoutPage;
import co.com.semillero.playwright.pages.InventarioPage;
import co.com.semillero.playwright.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas del flujo de compra en saucedemo.com (mismo escenario "compra de un producto" de la clase 2).
 * <p>
 * Muestra el <b>encadenamiento fluido</b> del POM: cada acción devuelve la página a la que lleva,
 * así la prueba se lee como el recorrido del usuario.
 */
@Tag("compra")
@DisplayName("Compra de productos en SauceDemo")
class CompraTest extends BaseTest {

    /** Datos de envío reutilizados por las pruebas de esta clase (constante: no cambian). */
    private static final DatosDeEnvio DATOS_DE_ENVIO = new DatosDeEnvio("Ana", "Pérez", "050001");

    /**
     * Escenario 3: un usuario compra un producto de principio a fin.
     */
    @Test
    @DisplayName("Compra exitosa de un producto")
    void compraDeUnProducto() {
        InventarioPage inventario = new LoginPage(pagina)
                .abrir()
                .iniciarSesionCon(Usuario.estandar())
                .agregarAlCarrito("Sauce Labs Backpack");
        assertThat(inventario.encabezado().contadorDelCarrito()).hasText("1");

        CarritoPage carrito = inventario.encabezado().irAlCarrito();
        assertThat(carrito.productosEnElCarrito()).hasText("Sauce Labs Backpack");

        CheckoutPage checkout = carrito.irAlCheckout().diligenciarDatosDeEnvio(DATOS_DE_ENVIO);
        // Ejemplo de lectura de texto + aserción de JUnit (no reintenta; úsala solo para valores ya cargados).
        assertTrue(checkout.totalDeLaOrden().startsWith("Total: $"), "El resumen debe mostrar el total");

        checkout.finalizarCompra();
        assertThat(checkout.mensajeDeConfirmacion()).hasText("Thank you for your order!");
    }

    /**
     * Data driven con {@code @CsvSource}: los datos van dentro de la anotación (útil para pocos casos).
     * Cada fila agrega dos productos y valida que el carrito los contenga en ese orden.
     *
     * @param primerProducto  nombre del primer producto a agregar
     * @param segundoProducto nombre del segundo producto a agregar
     */
    @ParameterizedTest(name = "{0} y {1}")
    @CsvSource({
            "Sauce Labs Backpack, Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt, Sauce Labs Onesie"
    })
    @DisplayName("Agregar dos productos al carrito")
    void agregarDosProductosAlCarrito(String primerProducto, String segundoProducto) {
        InventarioPage inventario = new LoginPage(pagina)
                .abrir()
                .iniciarSesionCon(Usuario.estandar())
                .agregarAlCarrito(primerProducto)
                .agregarAlCarrito(segundoProducto);
        assertThat(inventario.encabezado().contadorDelCarrito()).hasText("2");

        CarritoPage carrito = inventario.encabezado().irAlCarrito();
        // hasText con un arreglo valida la lista completa, elemento por elemento y en orden.
        assertThat(carrito.productosEnElCarrito()).hasText(new String[]{primerProducto, segundoProducto});
    }
}
