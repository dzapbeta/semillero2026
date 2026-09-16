package co.com.semillero.fundamentos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Prueba de humo ("smoke test") de la demo: comprueba que {@link App#main(String[])} corre completa.
 */
@DisplayName("Demo · App")
class AppTest {

    /**
     * Captura lo que imprime la demo (redirigiendo System.out) y revisa que aparezcan todas las secciones.
     */
    @Test
    @DisplayName("La demo recorre todas las secciones sin lanzar errores")
    void demoRecorreTodasLasSecciones() {
        PrintStream salidaOriginal = System.out;
        ByteArrayOutputStream capturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturada, true, StandardCharsets.UTF_8));
        try {
            App.main(new String[0]);
        } finally {
            System.setOut(salidaOriginal); // siempre se restaura, aunque la demo falle
        }

        String salida = capturada.toString(StandardCharsets.UTF_8);
        assertThat(salida)
                .contains("==== 1. Variables y tipos de datos ====")
                .contains("==== 2. Clases, objetos y encapsulamiento ====")
                .contains("==== 3. Polimorfismo ====")
                .contains("Intento con credenciales válidas -> Bienvenido, semillero");
    }
}
