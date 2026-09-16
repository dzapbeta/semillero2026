package co.com.semillero.playwright.tests;

import co.com.semillero.playwright.utils.Configuracion;
import co.com.semillero.playwright.utils.FabricaNavegador;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.nio.file.Path;
import java.text.Normalizer;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Clase padre de todas las pruebas: maneja el ciclo de vida del navegador con JUnit.
 * <p>
 * <b>Herencia:</b> {@code LoginTest} y {@code CompraTest} hacen {@code extends BaseTest} y reciben
 * lista la pestaña {@link #pagina}; así las pruebas solo contienen pasos y validaciones.
 * <pre>
 *  @BeforeAll  (1 vez por clase)   → Playwright + Browser  (costoso: se reutiliza)
 *  @BeforeEach (antes de cada test) → BrowserContext + Page + inicia traza (aislado: sesión limpia)
 *  @Test                            → la prueba
 *  (extensión)  justo al terminar   → si falló: captura de pantalla + traza en target/
 *  @AfterEach  (después de cada test) → cierra el contexto
 *  @AfterAll   (1 vez por clase)    → cierra navegador y Playwright
 * </pre>
 */
public abstract class BaseTest {

    /** Carpeta donde quedan las trazas (.zip) que se abren con "show-trace". */
    private static final Path CARPETA_TRAZAS = Paths.get("target", "trazas");

    /** Carpeta donde quedan las capturas de pantalla (.png) de las pruebas que fallan. */
    private static final Path CARPETA_CAPTURAS = Paths.get("target", "capturas");

    /** Motor de Playwright (arranca el proceso que controla los navegadores). Uno por clase de prueba. */
    private static Playwright playwright;

    /** Navegador abierto (chromium, firefox o webkit). Uno por clase de prueba. */
    private static Browser navegador;

    /** Contexto: como una ventana de incógnito nueva (cookies y sesión limpias). Uno por prueba. */
    private BrowserContext contexto;

    /** Pestaña que usan las pruebas para crear los Page Objects. Protegida: la ven las clases hijas. */
    protected Page pagina;

    /**
     * Extensión de JUnit que se ejecuta justo al terminar cada prueba y ANTES de {@code @AfterEach}
     * (cuando la página todavía está abierta). Si la prueba falló guarda captura y traza.
     */
    @RegisterExtension
    final AfterTestExecutionCallback guardarEvidenciaSiFalla = this::guardarEvidencia;

    /**
     * Se ejecuta una vez antes de todas las pruebas de la clase: crea Playwright y abre el navegador.
     */
    @BeforeAll
    static void abrirNavegador() {
        // PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1: no descarga los 3 navegadores en cada máquina al arrancar.
        // Cada quien instala solo el que usa (ver README: "Instalar los navegadores").
        playwright = Playwright.create(new Playwright.CreateOptions()
                .setEnv(Map.of("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1")));
        // Nuestros localizadores getByTestId(...) buscan el atributo data-test que usa saucedemo
        // (por defecto Playwright busca data-testid).
        playwright.selectors().setTestIdAttribute("data-test");
        // Tiempo máximo que reintentan las aserciones web-first antes de fallar.
        PlaywrightAssertions.setDefaultAssertionTimeout(Configuracion.timeoutMs());
        navegador = FabricaNavegador.crear(playwright);
    }

    /**
     * Se ejecuta antes de cada prueba: crea un contexto limpio, una pestaña y empieza a grabar la traza.
     */
    @BeforeEach
    void abrirPestana() {
        contexto = navegador.newContext();
        contexto.setDefaultTimeout(Configuracion.timeoutMs());
        // La traza graba capturas, el DOM de cada paso y el código fuente: es la "caja negra" de la prueba.
        contexto.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        pagina = contexto.newPage();
    }

    /**
     * Guarda la evidencia de la prueba que acaba de terminar.
     * Captura: solo si falló. Traza: si falló, o siempre cuando {@code guardarTraza=siempre}.
     *
     * @param contextoJUnit información de JUnit sobre la prueba (nombre, si lanzó excepción)
     */
    private void guardarEvidencia(ExtensionContext contextoJUnit) {
        boolean fallo = contextoJUnit.getExecutionException().isPresent();
        String nombre = nombreDeArchivo(contextoJUnit);
        if (fallo) {
            pagina.screenshot(new Page.ScreenshotOptions()
                    .setPath(CARPETA_CAPTURAS.resolve(nombre + ".png"))
                    .setFullPage(true));
        }
        if (fallo || "siempre".equals(Configuracion.guardarTraza())) {
            Path traza = CARPETA_TRAZAS.resolve(nombre + ".zip");
            contexto.tracing().stop(new Tracing.StopOptions().setPath(traza));
            System.out.println("Traza guardada: " + traza.toAbsolutePath());
        } else {
            contexto.tracing().stop();
        }
    }

    /**
     * Construye un nombre de archivo seguro: Clase_nombreDeLaPrueba (sin espacios ni símbolos).
     *
     * @param contextoJUnit información de JUnit sobre la prueba
     * @return nombre sin extensión, por ejemplo "LoginTest_Login_exitoso_con_usuario_estandar_1a2b3c4d"
     */
    private static String nombreDeArchivo(ExtensionContext contextoJUnit) {
        String clase = contextoJUnit.getRequiredTestClass().getSimpleName();
        String prueba = contextoJUnit.getDisplayName();
        // En pruebas parametrizadas el nombre visible se repite: se añade el id único de JUnit.
        String id = Integer.toHexString(contextoJUnit.getUniqueId().hashCode());
        // Normalizer quita las tildes (estándar → estandar) antes de reemplazar símbolos por "_".
        String sinTildes = Normalizer.normalize(clase + "_" + prueba, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sinTildes.replaceAll("[^A-Za-z0-9_-]+", "_") + "_" + id;
    }

    /**
     * Se ejecuta después de cada prueba: cierra el contexto (y con él la pestaña).
     */
    @AfterEach
    void cerrarPestana() {
        if (contexto != null) {
            contexto.close();
        }
    }

    /**
     * Se ejecuta una vez al final de la clase: cierra el navegador y Playwright para liberar memoria.
     */
    @AfterAll
    static void cerrarNavegador() {
        if (navegador != null) {
            navegador.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
