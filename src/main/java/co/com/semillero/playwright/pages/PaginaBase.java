package co.com.semillero.playwright.pages;

import co.com.semillero.playwright.utils.Configuracion;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * Clase padre de todas las páginas (Page Objects) del proyecto.
 * <p>
 * <b>Herencia:</b> {@code LoginPage}, {@code InventarioPage}, {@code CarritoPage} y {@code CheckoutPage}
 * hacen {@code extends PaginaBase} y así reciben, sin repetir código, la pestaña del navegador
 * ({@link Page}) y los métodos comunes (navegar, escribir, hacer clic, obtener texto).
 * <p>
 * Es {@code abstract} porque "una página base" no existe en la aplicación: solo sirve de molde.
 * Los métodos son {@code protected}: los usan las páginas hijas, pero no las pruebas
 * (las pruebas solo ven métodos de negocio como {@code iniciarSesionCon}).
 */
public abstract class PaginaBase {

    /** Pestaña del navegador donde vive la página. Protegida: visible para las páginas hijas. */
    protected final Page pagina;

    /**
     * Constructor que reciben todas las páginas hijas mediante {@code super(pagina)}.
     *
     * @param pagina pestaña del navegador creada en la prueba (BaseTest)
     */
    protected PaginaBase(Page pagina) {
        this.pagina = pagina;
    }

    /**
     * Abre una ruta de la aplicación a partir de la URL base configurada.
     *
     * @param ruta ruta relativa, por ejemplo "/" o "/inventory.html"
     */
    protected void navegar(String ruta) {
        pagina.navigate(Configuracion.baseUrl() + ruta);
    }

    /**
     * Escribe un texto en un campo. {@code fill} espera solo a que el campo esté visible y habilitado
     * (auto-waiting), borra lo que tenga y escribe el valor nuevo.
     *
     * @param campo localizador del campo de texto
     * @param texto valor a escribir
     */
    protected void escribir(Locator campo, String texto) {
        campo.fill(texto);
    }

    /**
     * Hace clic en un elemento. {@code click} espera a que sea visible, estable y habilitado.
     *
     * @param elemento localizador del botón, enlace o control
     */
    protected void hacerClic(Locator elemento) {
        elemento.click();
    }

    /**
     * Espera a que un elemento esté visible (útil para confirmar que una página ya cargó).
     *
     * @param elemento localizador que debe aparecer
     */
    protected void esperarVisible(Locator elemento) {
        elemento.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    /**
     * Devuelve el texto visible de un elemento, sin espacios alrededor.
     * {@code innerText} espera a que el elemento exista, pero la comparación que hagas después con
     * JUnit NO se reintenta: para validar textos prefiere {@code assertThat(locator).hasText(...)}.
     *
     * @param elemento localizador del que se quiere leer el texto
     * @return el texto del elemento
     */
    protected String obtenerTexto(Locator elemento) {
        return elemento.innerText().trim();
    }
}
