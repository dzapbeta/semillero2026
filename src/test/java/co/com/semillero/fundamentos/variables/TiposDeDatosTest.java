package co.com.semillero.fundamentos.variables;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Pruebas unitarias de {@link TiposDeDatos}.
 *
 * <p>Estructura de cada prueba (patrón AAA): <b>Arrange</b> (preparar datos), <b>Act</b> (ejecutar)
 * y <b>Assert</b> (verificar con AssertJ).</p>
 */
@DisplayName("Tema 1 · Tipos de datos")
class TiposDeDatosTest {

    /** Verifica que la conversión de milisegundos conserve los decimales. */
    @Test
    @DisplayName("Convierte 1500 ms en 1.5 segundos sin perder decimales")
    void convierteMilisegundosASegundos() {
        double segundos = TiposDeDatos.milisegundosASegundos(1500);

        assertThat(segundos).isEqualTo(1.5);
    }

    /** Verifica que el casting explícito a int trunque y no redondee. */
    @Test
    @DisplayName("El casting (int) descarta los decimales, no redondea")
    void castingTruncaDecimales() {
        assertThat(TiposDeDatos.truncarPorcentaje(99.9)).isEqualTo(99);
    }

    /** Verifica los métodos de String usados para normalizar un nombre. */
    @Test
    @DisplayName("Normaliza el nombre del caso: sin espacios extremos, minúsculas y guion bajo")
    void normalizaNombreDeCaso() {
        assertThat(TiposDeDatos.normalizarNombreCaso("  Login Exitoso ")).isEqualTo("login_exitoso");
    }

    /** Caso negativo: un nombre vacío debe lanzar excepción con un mensaje claro. */
    @Test
    @DisplayName("Rechaza un nombre de caso vacío")
    void rechazaNombreVacio() {
        assertThatThrownBy(() -> TiposDeDatos.normalizarNombreCaso("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("vacío");
    }

    /** Verifica ids válidos e inválidos. */
    @Test
    @DisplayName("Valida el formato del id de caso CP-000")
    void validaIdDeCaso() {
        assertThat(TiposDeDatos.esIdDeCasoValido("CP-001")).isTrue();
        assertThat(TiposDeDatos.esIdDeCasoValido("CP-1")).isFalse();
        assertThat(TiposDeDatos.esIdDeCasoValido("CP-ABC")).isFalse();
        assertThat(TiposDeDatos.esIdDeCasoValido(null)).isFalse();
    }

    /** Verifica la conversión de texto a Integer (wrapper). */
    @Test
    @DisplayName("Convierte el texto \"200\" al número 200")
    void convierteTextoANumero() {
        assertThat(TiposDeDatos.textoANumero(" 200 ")).isEqualTo(200);
    }

    /** Caso negativo: un texto que no es número lanza NumberFormatException. */
    @Test
    @DisplayName("Lanza NumberFormatException si el texto no es un número")
    void textoNoNumericoLanzaExcepcion() {
        assertThatThrownBy(() -> TiposDeDatos.textoANumero("abc"))
                .isInstanceOf(NumberFormatException.class);
    }

    /** Verifica que se compare el contenido de los textos con equals. */
    @Test
    @DisplayName("Compara textos por contenido con equals")
    void comparaTextosPorContenido() {
        String obtenido = new String("Bienvenido"); // objeto distinto en memoria, mismo contenido
        assertThat(TiposDeDatos.mismoTexto("Bienvenido", obtenido)).isTrue();
        assertThat(TiposDeDatos.mismoTexto("Bienvenido", "Error")).isFalse();
    }
}
