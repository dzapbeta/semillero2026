package co.com.semillero.fundamentos.poo.polimorfismo;

import co.com.semillero.fundamentos.poo.encapsulamiento.CasoDePrueba;
import co.com.semillero.fundamentos.poo.encapsulamiento.Defecto;
import co.com.semillero.fundamentos.poo.encapsulamiento.Severidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pruebas unitarias de {@link Reportero}: polimorfismo estático (sobrecarga).
 */
@DisplayName("Tema 3 · Sobrecarga con Reportero")
class ReporteroTest {

    /** Objeto bajo prueba; no tiene estado, así que se puede compartir entre pruebas. */
    private final Reportero reportero = new Reportero();

    /** Verifica que Java elija la sobrecarga según el tipo del argumento. */
    @Test
    @DisplayName("formatear elige la versión correcta según el tipo del argumento")
    void eligeSobrecargaSegunTipo() {
        CasoDePrueba caso = new CasoDePrueba("CP-001", "Login");
        Defecto defecto = new Defecto("BUG-1", "No carga el login", Severidad.ALTA, caso);

        assertThat(reportero.formatear(caso)).isEqualTo("CASO CP-001 - Login: PENDIENTE");
        assertThat(reportero.formatear(defecto)).isEqualTo("DEFECTO BUG-1 (ALTA) - No carga el login");
    }

    /** Verifica la sobrecarga con dos parámetros, en sus dos ramas. */
    @Test
    @DisplayName("formatear(String, boolean) muestra OK o KO")
    void formateaNombreYResultado() {
        assertThat(reportero.formatear("Login", true)).isEqualTo("Login -> OK");
        assertThat(reportero.formatear("Login", false)).isEqualTo("Login -> KO");
    }

    /** Verifica la sobrecarga del resumen. */
    @Test
    @DisplayName("formatear(ResumenEjecucion) muestra totales y porcentaje")
    void formateaResumen() {
        ResumenEjecucion resumen = new ResumenEjecucion(4, 3, List.of("Pagar"));

        assertThat(reportero.formatear(resumen))
                .isEqualTo("Total: 4 | Exitosas: 3 | Fallidas: 1 | Éxito: 75.0 %");
    }
}
