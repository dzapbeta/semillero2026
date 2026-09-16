package co.com.semillero.control;

import java.util.List;

// Ejemplos de ciclos: repetir una instrucción varias veces sin escribirla muchas veces.
public class Ciclos {

    // for: se usa cuando sabes cuántas veces quieres repetir. Aquí cuenta de 1 a 5.
    public static void contarConFor() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Vuelta numero " + i);
        }
    }

    // while: repite mientras la condición sea verdadera. Aquí simula 3 intentos de login.
    public static void intentosConWhile() {
        int intento = 1;
        while (intento <= 3) {
            System.out.println("Intento de login " + intento);
            intento++;
        }
    }

    // for-each: recorre una lista elemento por elemento. Aquí saluda a cada estudiante.
    public static void saludarConForEach() {
        List<String> nombres = List.of("Ana", "Luis", "Camila");
        for (String nombre : nombres) {
            System.out.println("Hola, " + nombre);
        }
    }
}
