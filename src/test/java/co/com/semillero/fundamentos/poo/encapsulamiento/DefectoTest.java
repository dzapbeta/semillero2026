package co.com.semillero.fundamentos.poo.encapsulamiento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Pruebas unitarias de {@link Defecto} y del enum {@link Severidad}.
 */
@DisplayName("Tema 2 · Defecto y Severidad")
class DefectoTest {

    /** Caso de prueba reutilizado como "caso que detectó el defecto". */
    private final CasoDePrueba caso = new CasoDePrueba("CP-020", "Pagar con tarjeta");

    /** Verifica un defecto válido recién creado. */
    @Test
    @DisplayName("Un defecto nuevo queda abierto y asociado a su caso")
    void defectoNuevoQuedaAbierto() {
        Defecto defecto = new Defecto("BUG-101", "Botón Pagar no responde", Severidad.CRITICA, caso);

        assertThat(defecto.isAbierto()).isTrue();
        assertThat(defecto.getCasoQueLoDetecto().getId()).isEqualTo("CP-020");
        assertThat(defecto.bloqueaSalida()).isTrue();
    }

    /** Verifica el ciclo cerrar / reabrir y sus validaciones. */
    @Test
    @DisplayName("Cerrar dos veces seguidas lanza error; reabrir lo vuelve a abrir")
    void cicloDeVida() {
        Defecto defecto = new Defecto("BUG-102", "Mensaje de error sin tilde", Severidad.BAJA, caso);
        defecto.cerrar();

        assertThat(defecto.isAbierto()).isFalse();
        assertThatThrownBy(defecto::cerrar).isInstanceOf(IllegalStateException.class);

        defecto.reabrir();
        assertThat(defecto.isAbierto()).isTrue();
    }

    /** Caso negativo: título demasiado corto. */
    @Test
    @DisplayName("Rechaza un título con menos de 10 caracteres")
    void rechazaTituloCorto() {
        assertThatThrownBy(() -> new Defecto("BUG-103", "Falla", Severidad.MEDIA, caso))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("10");
    }

    /** Caso negativo: la severidad es obligatoria. */
    @Test
    @DisplayName("Rechaza un defecto sin severidad")
    void rechazaSeveridadNula() {
        assertThatThrownBy(() -> new Defecto("BUG-104", "Pantalla en blanco al pagar", null, caso))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("La severidad es obligatoria");
    }

    /** Verifica que un defecto cerrado o de baja severidad no bloquee la salida. */
    @Test
    @DisplayName("Solo bloquean la salida los defectos abiertos CRITICA o ALTA")
    void reglaDeBloqueoDeSalida() {
        Defecto defecto = new Defecto("BUG-105", "Logo pixelado en el header", Severidad.BAJA, caso);
        assertThat(defecto.bloqueaSalida()).isFalse();

        defecto.cambiarSeveridad(Severidad.ALTA);
        assertThat(defecto.bloqueaSalida()).isTrue();

        defecto.cerrar();
        assertThat(defecto.bloqueaSalida()).isFalse();
        assertThat(Severidad.CRITICA.getNivel()).isGreaterThan(Severidad.MEDIA.getNivel());
    }
}
