package co.com.semillero.fundamentos.variables;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

/**
 * Pruebas unitarias de {@link Colecciones}.
 */
@DisplayName("Tema 1 · Arreglos y colecciones")
class ColeccionesTest {

    /** Verifica la búsqueda en el arreglo, positiva y negativa. */
    @Test
    @DisplayName("Reconoce navegadores soportados sin importar mayúsculas y rechaza Safari")
    void reconoceNavegadoresSoportados() {
        assertThat(Colecciones.esNavegadorSoportado("chrome")).isTrue();
        assertThat(Colecciones.esNavegadorSoportado("Safari")).isFalse();
    }

    /** Verifica que el filtro devuelva solo los casos con el prefijo y sin tocar la lista original. */
    @Test
    @DisplayName("Filtra los casos de Login en una lista nueva")
    void filtraPorPrefijo() {
        List<String> casos = List.of("Login ok", "Buscar", "Login fallido");

        assertThat(Colecciones.filtrarPorPrefijo(casos, "Login")).containsExactly("Login ok", "Login fallido");
        assertThat(casos).hasSize(3);
    }

    /** Verifica el conteo con Map. */
    @Test
    @DisplayName("Cuenta los casos por estado con un Map")
    void cuentaPorEstado() {
        assertThat(Colecciones.contarPorEstado(List.of("EXITOSO", "FALLIDO", "EXITOSO")))
                .containsOnly(entry("EXITOSO", 2), entry("FALLIDO", 1));
    }

    /** Verifica que el Set elimine repetidos y conserve el orden. */
    @Test
    @DisplayName("Elimina defectos duplicados con un Set conservando el orden")
    void eliminaDuplicados() {
        assertThat(Colecciones.eliminarDuplicados(List.of("BUG-1", "BUG-2", "BUG-1")))
                .containsExactly("BUG-1", "BUG-2");
    }

    /** Caso borde: una lista vacía produce un mapa vacío. */
    @Test
    @DisplayName("Una lista vacía de estados produce un conteo vacío")
    void listaVaciaProduceMapaVacio() {
        assertThat(Colecciones.contarPorEstado(List.of())).isEmpty();
    }
}
