package co.com.semillero.playwright.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

// Clase padre de las pruebas. Abre y cierra el navegador para que las pruebas solo tengan pasos y validaciones.
// LoginTest la hereda con "extends BaseTest".
public class BaseTest {

    // Motor de Playwright. Se crea una sola vez para todas las pruebas de la clase.
    private static Playwright playwright;

    // Navegador Chromium. También se abre una sola vez porque abrirlo tarda.
    private static Browser navegador;

    // Pestaña nueva para cada prueba. Es protected para que LoginTest la pueda usar.
    protected Page pagina;

    // Antes de todas las pruebas: arranca Playwright y abre Chromium.
    // Lee -Dheadless: true trabaja sin ventana, false muestra el navegador.
    @BeforeAll
    static void abrirNavegador() {
        boolean sinVentana = Boolean.parseBoolean(System.getProperty("headless", "true"));
        playwright = Playwright.create();
        navegador = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(sinVentana));
    }

    // Antes de cada prueba: abre una pestaña limpia, sin sesión ni datos de la prueba anterior.
    @BeforeEach
    void abrirPagina() {
        pagina = navegador.newPage();
    }

    // Después de cada prueba: cierra la pestaña.
    @AfterEach
    void cerrarPagina() {
        pagina.close();
    }

    // Después de todas las pruebas: cierra el navegador y Playwright.
    @AfterAll
    static void cerrarNavegador() {
        navegador.close();
        playwright.close();
    }
}
