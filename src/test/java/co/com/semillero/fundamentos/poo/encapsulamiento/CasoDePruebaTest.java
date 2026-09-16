package co.com.semillero.fundamentos.poo.encapsulamiento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Pruebas unitarias de {@link CasoDePrueba}: comprueban que el encapsulamiento proteja las reglas.
 */
@DisplayName("Tema 2 · CasoDePrueba (encapsulamiento)")
class CasoDePruebaTest {

    /** Objeto bajo prueba. Se crea de nuevo antes de CADA prueba para que no se contaminen entre sí. */
    private CasoDePrueba caso;

    /** {@code @BeforeEach}: se ejecuta antes de cada {@code @Test} y deja un caso con un paso. */
    @BeforeEach
    void prepararCaso() {
        caso = new CasoDePrueba("CP-001", "Login con credenciales válidas");
        caso.agregarPaso("Abrir la página de login");
    }

    /** Verifica el estado inicial de un objeto recién creado. */
    @Test
    @DisplayName("Un caso nuevo queda PENDIENTE y sin ejecuciones")
    void casoNuevoQuedaPendiente() {
        assertThat(caso.getEstado()).isEqualTo(EstadoCaso.PENDIENTE);
        assertThat(caso.getEjecuciones()).isZero();
        assertThat(caso.getId()).isEqualTo("CP-001");
    }

    /** Verifica que registrar ejecuciones cambie el estado y el contador. */
    @Test
    @DisplayName("Registrar una ejecución exitosa y luego una fallida actualiza estado y contador")
    void registraEjecuciones() {
        caso.registrarEjecucion(true);
        assertThat(caso.getEstado()).isEqualTo(EstadoCaso.EXITOSO);

        caso.registrarEjecucion(false);
        assertThat(caso.getEstado()).isEqualTo(EstadoCaso.FALLIDO);
        assertThat(caso.getEjecuciones()).isEqualTo(2);
        assertThat(caso.getEstado().fueEjecutado()).isTrue();
    }

    /** Caso negativo: el constructor rechaza un id con formato inválido. */
    @Test
    @DisplayName("No permite crear un caso con id inválido")
    void rechazaIdInvalido() {
        assertThatThrownBy(() -> new CasoDePrueba("caso1", "Título"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CP-000");
    }

    /** Caso negativo: el constructor rechaza un título vacío. */
    @Test
    @DisplayName("No permite crear un caso con título vacío")
    void rechazaTituloVacio() {
        assertThatThrownBy(() -> new CasoDePrueba("CP-002", " "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /** Caso negativo: un caso bloqueado no se puede ejecutar. */
    @Test
    @DisplayName("Un caso bloqueado no se puede ejecutar y conserva el motivo")
    void casoBloqueadoNoSeEjecuta() {
        caso.bloquear("Ambiente de QA caído");

        assertThatThrownBy(() -> caso.registrarEjecucion(true))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Ambiente de QA caído");
        assertThat(caso.getEjecuciones()).isZero();
    }

    /** Verifica que desbloquear devuelva el caso a PENDIENTE. */
    @Test
    @DisplayName("Desbloquear deja el caso PENDIENTE y limpia el motivo")
    void desbloquearDejaPendiente() {
        caso.bloquear("Ambiente caído");
        caso.desbloquear();

        assertThat(caso.getEstado()).isEqualTo(EstadoCaso.PENDIENTE);
        assertThat(caso.getMotivoBloqueo()).isEmpty();
    }

    /** Caso negativo: no se ejecuta un caso sin pasos. */
    @Test
    @DisplayName("Un caso sin pasos no se puede ejecutar")
    void casoSinPasosNoSeEjecuta() {
        CasoDePrueba sinPasos = new CasoDePrueba("CP-003", "Caso sin pasos");

        assertThatThrownBy(() -> sinPasos.registrarEjecucion(true))
                .isInstanceOf(IllegalStateException.class);
    }

    /** Verifica que la lista de pasos no se pueda modificar desde afuera. */
    @Test
    @DisplayName("La lista de pasos que se entrega es de solo lectura")
    void pasosSonDeSoloLectura() {
        assertThatThrownBy(() -> caso.getPasos().clear())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(caso.getPasos()).hasSize(1);
    }

    /** Verifica el formato de toString. */
    @Test
    @DisplayName("toString muestra id, título, estado y contadores")
    void toStringMuestraResumen() {
        assertThat(caso.toString()).isEqualTo("CP-001 | Login con credenciales válidas | PENDIENTE | pasos=1 | ejecuciones=0");
    }
}
