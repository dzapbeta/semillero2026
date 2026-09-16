package co.com.semillero.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

// Page Object de la pantalla de productos, la que aparece después de un login exitoso.
public class ProductosPage extends PaginaBase {

    // Título de la pantalla, que dice "Products".
    private final Locator titulo;

    // Recibe la pestaña, se la pasa a PaginaBase y prepara el localizador del título.
    public ProductosPage(Page pagina) {
        super(pagina);
        titulo = pagina.locator("[data-test='title']");
    }

    // Devuelve el texto del título. Si el login funcionó, debe ser "Products".
    public String titulo() {
        return titulo.textContent();
    }
}
