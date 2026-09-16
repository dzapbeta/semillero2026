package co.com.semillero.playwright.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

/**
 * Fábrica que crea el navegador ({@link Browser}) indicado en la configuración.
 * <p>
 * <b>Polimorfismo:</b> {@link BrowserType} es una interfaz. {@code playwright.chromium()},
 * {@code playwright.firefox()} y {@code playwright.webkit()} devuelven objetos distintos, pero
 * todos se usan igual: {@code tipo.launch(opciones)}. El resto del código no sabe (ni le importa)
 * qué navegador real hay detrás.
 */
public final class FabricaNavegador {

    /** Constructor privado: la fábrica solo expone un método estático. */
    private FabricaNavegador() {
    }

    /**
     * Lanza el navegador configurado (chromium, firefox o webkit).
     *
     * @param playwright instancia de Playwright ya creada
     * @return el navegador abierto, listo para crear contextos
     */
    public static Browser crear(Playwright playwright) {
        // Se elige la implementación concreta; a partir de aquí todo se trata como BrowserType.
        BrowserType tipo = switch (Configuracion.navegador()) {
            case "chromium" -> playwright.chromium();
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> throw new IllegalArgumentException(
                    "Navegador no soportado: " + Configuracion.navegador() + " (usa chromium, firefox o webkit)");
        };
        // Opciones comunes para cualquier navegador: con o sin ventana y pausa entre acciones.
        BrowserType.LaunchOptions opciones = new BrowserType.LaunchOptions()
                .setHeadless(Configuracion.headless())
                .setSlowMo(Configuracion.slowMo());
        return tipo.launch(opciones);
    }
}
