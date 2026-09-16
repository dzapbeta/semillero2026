package co.com.semillero.control;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Pruebas de los ejemplos de if/else y switch.
class CondicionalesTest {

    // Una nota por encima de 3.0 debe aprobar.
    @Test
    @DisplayName("Una nota de 4.5 queda Aprobado")
    void notaAltaApruebaTest() {
        assertEquals("Aprobado", Condicionales.evaluarNota(4.5));
    }

    // El límite exacto también aprueba, porque la condición es >= y no solo >.
    @Test
    @DisplayName("Una nota de 3.0 justa queda Aprobado")
    void notaLimiteApruebaTest() {
        assertEquals("Aprobado", Condicionales.evaluarNota(3.0));
    }

    // Una nota por debajo de 3.0 debe reprobar.
    @Test
    @DisplayName("Una nota de 2.9 queda Reprobado")
    void notaBajaRepruebaTest() {
        assertEquals("Reprobado", Condicionales.evaluarNota(2.9));
    }

    // Con el semáforo en rojo el switch debe responder Pare.
    @Test
    @DisplayName("El semaforo en rojo dice Pare")
    void semaforoRojoTest() {
        assertEquals("Pare", Condicionales.accionSemaforo("rojo"));
    }

    // Un color que no existe cae en el default del switch.
    @Test
    @DisplayName("Un color que no existe responde Color desconocido")
    void semaforoColorInvalidoTest() {
        assertEquals("Color desconocido", Condicionales.accionSemaforo("azul"));
    }
}
