package co.com.semillero.fundamentos.variables;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Pruebas unitarias de {@link EstructurasDeControl}.
 */
@DisplayName("Tema 1 · Estructuras de control")
class EstructurasDeControlTest {

    /**
     * Prueba parametrizada: la MISMA prueba se ejecuta una vez por cada fila de datos.
     * Es la semilla de lo que luego será "data driven".
     *
     * @param impacto            dato de entrada
     * @param severidadEsperada  resultado esperado
     */
    @ParameterizedTest(name = "impacto {0} -> {1}")
    @CsvSource({"10, CRITICA", "9, CRITICA", "7, ALTA", "4, MEDIA", "1, BAJA"})
    @DisplayName("Clasifica la severidad según el impacto (if/else)")
    void clasificaSeveridad(int impacto, String severidadEsperada) {
        assertThat(EstructurasDeControl.clasificarSeveridad(impacto)).isEqualTo(severidadEsperada);
    }

    /** Caso negativo: impacto fuera de rango. */
    @Test
    @DisplayName("Rechaza un impacto fuera del rango 1..10")
    void rechazaImpactoFueraDeRango() {
        assertThatThrownBy(() -> EstructurasDeControl.clasificarSeveridad(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("entre 1 y 10");
    }

    /** Verifica la switch expression, incluido el caso default. */
    @Test
    @DisplayName("Describe el resultado con switch, incluido un código desconocido")
    void describeResultado() {
        assertThat(EstructurasDeControl.describirResultado("P")).isEqualTo("Pasó");
        assertThat(EstructurasDeControl.describirResultado("O")).isEqualTo("Saltado u omitido");
        assertThat(EstructurasDeControl.describirResultado("X")).isEqualTo("Código desconocido: X");
    }

    /** Verifica el cálculo del porcentaje y el caso borde de total cero. */
    @Test
    @DisplayName("Calcula el porcentaje de éxito y devuelve 0 si no hubo ejecuciones")
    void calculaPorcentajeDeExito() {
        assertThat(EstructurasDeControl.calcularPorcentajeExito(45, 50)).isEqualTo(90.0);
        assertThat(EstructurasDeControl.calcularPorcentajeExito(0, 0)).isZero();
    }

    /** Caso negativo: más exitosos que total no tiene sentido. */
    @Test
    @DisplayName("Rechaza más casos exitosos que el total")
    void rechazaDatosInconsistentes() {
        assertThatThrownBy(() -> EstructurasDeControl.calcularPorcentajeExito(6, 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /** Verifica for-each, for con break y continue. */
    @Test
    @DisplayName("Cuenta fallidos, encuentra el primero y omite bloqueados (for, break, continue)")
    void recorreResultados() {
        boolean[] resultados = {true, false, true, false};
        String[] nombres = {"Login", "Pagar", "Buscar", "Salir"};

        assertThat(EstructurasDeControl.contarFallidos(resultados)).isEqualTo(2);
        assertThat(EstructurasDeControl.buscarPrimerFallido(nombres, resultados)).isEqualTo("Pagar");
        assertThat(EstructurasDeControl.buscarPrimerFallido(nombres, new boolean[] {true, true, true, true}))
                .isEqualTo("Ninguno");
        assertThat(EstructurasDeControl.contarEjecutadosSinBloqueados(new String[] {"P", "B", "F"})).isEqualTo(2);
    }

    /** Verifica el while de reintentos: pasa a tiempo y se agotan los intentos. */
    @Test
    @DisplayName("Reintenta una prueba inestable y devuelve -1 si se agotan los intentos (while)")
    void reintentaPruebaInestable() {
        assertThat(EstructurasDeControl.ejecutarConReintentos(2, 3)).isEqualTo(3);
        assertThat(EstructurasDeControl.ejecutarConReintentos(3, 3)).isEqualTo(-1);
    }

    /** Verifica que do-while se ejecute al menos una vez. */
    @Test
    @DisplayName("do-while revisa la página al menos una vez")
    void doWhileSeEjecutaAlMenosUnaVez() {
        assertThat(EstructurasDeControl.esperarCargaDePagina(0)).isEqualTo(1);
        assertThat(EstructurasDeControl.esperarCargaDePagina(4)).isEqualTo(4);
    }
}
