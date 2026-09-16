package co.com.semillero.fundamentos.paginas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Pruebas de {@link PaginaLogin}. Se leen como una prueba automatizada real con POM:
 * la prueba habla de negocio ("iniciar sesión") y no conoce los localizadores.
 */
@DisplayName("Puente · PaginaLogin (Page Object simulado)")
class PaginaLoginTest {

    /** Página bajo prueba, nueva para cada prueba. */
    private PaginaLogin pagina;

    /** Crea la página antes de cada prueba (sin abrirla: cada prueba decide). */
    @BeforeEach
    void crearPagina() {
        pagina = new PaginaLogin();
    }

    /** Camino feliz: credenciales válidas. */
    @Test
    @DisplayName("Con credenciales válidas muestra el mensaje de bienvenida")
    void loginExitoso() {
        pagina.abrir();

        pagina.iniciarSesion(PaginaLogin.USUARIO_VALIDO, PaginaLogin.CLAVE_VALIDA);

        assertThat(pagina.obtenerMensaje()).isEqualTo(PaginaLogin.MENSAJE_BIENVENIDA);
    }

    /** Caso negativo: clave incorrecta. */
    @Test
    @DisplayName("Con clave incorrecta muestra el mensaje de error")
    void loginConClaveIncorrecta() {
        pagina.abrir();

        pagina.iniciarSesion(PaginaLogin.USUARIO_VALIDO, "otra-clave");

        assertThat(pagina.obtenerMensaje()).isEqualTo(PaginaLogin.MENSAJE_ERROR);
    }

    /** Caso negativo: la regla heredada de PaginaBase impide interactuar sin abrir. */
    @Test
    @DisplayName("No permite iniciar sesión si la página no se ha abierto")
    void noInteractuaSinAbrir() {
        assertThatThrownBy(() -> pagina.iniciarSesion("semillero", "Qa2026*"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Primero hay que abrir");
        assertThat(pagina.isAbierta()).isFalse();
    }

    /** Verifica que las acciones heredadas queden registradas en orden. */
    @Test
    @DisplayName("Registra en orden las acciones heredadas de PaginaBase")
    void registraAcciones() {
        pagina.abrir();
        pagina.iniciarSesion("semillero", "x");

        assertThat(pagina.getAcciones()).containsExactly(
                "abrir https://semillero.qa/login",
                "escribir en #usuario",
                "escribir en #clave",
                "clic en #ingresar");
    }
}
