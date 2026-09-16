package co.com.semillero.fundamentos.poo.herencia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Pruebas unitarias de la jerarquía {@link Navegador} → {@link Chrome}, {@link Firefox}, {@link Edge}.
 */
@DisplayName("Tema 3 · Herencia de navegadores")
class NavegadorTest {

    /** Verifica que cada hijo implemente su propio driver (método abstracto). */
    @Test
    @DisplayName("Cada navegador hijo indica su propio driver")
    void cadaHijoTieneSuDriver() {
        assertThat(new Chrome(false, false).nombreDelDriver()).isEqualTo("chromedriver");
        assertThat(new Firefox(false).nombreDelDriver()).isEqualTo("geckodriver");
        assertThat(new Edge(false).nombreDelDriver()).isEqualTo("msedgedriver");
    }

    /** Verifica que lo heredado (abrir + historial) funcione en un hijo. */
    @Test
    @DisplayName("Chrome hereda abrir() y el historial del padre")
    void chromeHeredaAbrir() {
        Chrome chrome = new Chrome(true, false);

        String mensaje = chrome.abrir("https://semillero.qa");

        assertThat(mensaje).isEqualTo("Chrome abrió https://semillero.qa (sin ventana)");
        assertThat(chrome.getHistorial()).containsExactly("https://semillero.qa");
    }

    /** Verifica la sobrescritura que reutiliza al padre con super. */
    @Test
    @DisplayName("Chrome y Firefox sobrescriben métodos reutilizando super")
    void sobrescrituraConSuper() {
        assertThat(new Chrome(false, true).describir())
                .isEqualTo("Chrome | driver=chromedriver | modo=con ventana | incognito=true");
        assertThat(new Firefox(false).abrir("http://localhost:8080"))
                .endsWith("[perfil limpio de Firefox]");
    }

    /** Caso negativo: la validación del padre se aplica a todos los hijos. */
    @Test
    @DisplayName("Rechaza una URL sin http/https en cualquier navegador")
    void rechazaUrlInvalida() {
        assertThatThrownBy(() -> new Firefox(false).abrir("semillero.qa"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("URL inválida");
    }

    /** Caso negativo: Edge endurece la validación protegida del padre. */
    @Test
    @DisplayName("Edge solo abre sitios https (sobrescribe la validación protegida)")
    void edgeExigeHttps() {
        Edge edge = new Edge(false);

        assertThatThrownBy(() -> edge.abrir("http://semillero.qa"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("https");
        assertThat(edge.getHistorial()).isEmpty();
    }

    /** Verifica que una variable del tipo padre pueda guardar cualquier hijo. */
    @Test
    @DisplayName("Una lista de tipo Navegador acepta Chrome, Firefox y Edge")
    void listaDelTipoPadre() {
        List<Navegador> navegadores = List.of(new Chrome(false, false), new Firefox(false), new Edge(false));

        assertThat(navegadores).extracting(Navegador::getNombre).containsExactly("Chrome", "Firefox", "Edge");
        assertThat(navegadores.get(0)).isInstanceOf(Navegador.class);
    }
}
