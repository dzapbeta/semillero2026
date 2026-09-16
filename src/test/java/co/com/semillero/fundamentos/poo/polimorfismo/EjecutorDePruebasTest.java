package co.com.semillero.fundamentos.poo.polimorfismo;

import co.com.semillero.fundamentos.poo.herencia.Chrome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Pruebas unitarias de {@link EjecutorDePruebas}: polimorfismo dinámico con {@link Ejecutable}.
 */
@DisplayName("Tema 3 · Polimorfismo con EjecutorDePruebas")
class EjecutorDePruebasTest {

    /** Verifica que el ejecutor trate igual a pruebas web, API y móvil y cuente bien. */
    @Test
    @DisplayName("Ejecuta una lista mixta (web, API, móvil) y resume exitosas y fallidas")
    void ejecutaListaMixta() {
        List<Ejecutable> pruebas = List.of(
                new PruebaWeb("Login", new Chrome(true, false), "https://semillero.qa", "Hola", "Hola"),
                new PruebaApi("Consultar usuario", "GET /usuarios/1", 200, 500),
                new PruebaMovil("Ver saldo", "Pixel 8", true),
                new PruebaMovil("Transferir", "iPhone 15", false));

        ResumenEjecucion resumen = new EjecutorDePruebas().ejecutarTodas(pruebas);

        assertThat(resumen.getTotal()).isEqualTo(4);
        assertThat(resumen.getExitosas()).isEqualTo(2);
        assertThat(resumen.getFallidas()).isEqualTo(2);
        assertThat(resumen.getPorcentajeExito()).isEqualTo(50.0);
        assertThat(resumen.getNombresFallidas()).containsExactly("Consultar usuario", "Transferir");
    }

    /** Verifica que cada objeto use SU versión de describir() (sobrescrita o default). */
    @Test
    @DisplayName("La bitácora usa la descripción propia de cada tipo de prueba")
    void bitacoraPolimorfica() {
        EjecutorDePruebas ejecutor = new EjecutorDePruebas();
        ejecutor.ejecutarTodas(List.of(
                new PruebaWeb("Login", new Chrome(true, false), "https://semillero.qa", "A", "B"),
                new PruebaApi("Crear usuario", "POST /usuarios", 201, 201)));

        assertThat(ejecutor.getBitacora())
                .containsExactly("FALLÓ [Web · Chrome] Login", "PASÓ  [PruebaApi] Crear usuario");
    }

    /** Verifica que la PruebaWeb realmente use el navegador recibido. */
    @Test
    @DisplayName("La prueba web abre la URL en el navegador que recibe")
    void pruebaWebUsaElNavegador() {
        Chrome chrome = new Chrome(true, false);

        new PruebaWeb("Login", chrome, "https://semillero.qa/login", "OK", "OK").ejecutar();

        assertThat(chrome.getHistorial()).containsExactly("https://semillero.qa/login");
    }

    /** Caso borde: lista vacía. */
    @Test
    @DisplayName("Una lista vacía produce un resumen en cero")
    void listaVacia() {
        ResumenEjecucion resumen = new EjecutorDePruebas().ejecutarTodas(List.of());

        assertThat(resumen.getTotal()).isZero();
        assertThat(resumen.getPorcentajeExito()).isZero();
    }

    /** Caso negativo: lista nula. */
    @Test
    @DisplayName("Rechaza una lista de pruebas nula")
    void rechazaListaNula() {
        assertThatThrownBy(() -> new EjecutorDePruebas().ejecutarTodas(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
