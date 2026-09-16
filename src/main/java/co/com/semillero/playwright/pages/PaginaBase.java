package co.com.semillero.playwright.pages;

import com.microsoft.playwright.Page;

// Clase padre de todas las páginas. Guarda la pestaña del navegador y tiene lo que todas comparten.
// LoginPage y ProductosPage la heredan con "extends PaginaBase".
public class PaginaBase {

    // Pestaña del navegador con la que trabaja la página. Es protected para que las clases hijas la usen.
    protected final Page pagina;

    // Recibe la pestaña que abrió la prueba y la guarda.
    public PaginaBase(Page pagina) {
        this.pagina = pagina;
    }

    // Lleva la pestaña a la dirección que le pases.
    public void navegarA(String direccion) {
        pagina.navigate(direccion);
    }

    // Devuelve el texto que se ve en la pestaña del navegador (por ejemplo "Swag Labs").
    public String tituloDeLaPestana() {
        return pagina.title();
    }
}
