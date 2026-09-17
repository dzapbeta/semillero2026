package co.com.semillero.control;

import java.util.List;

// Clase Ciclos: ejemplos de cómo repetir instrucciones sin escribirlas muchas veces.
public class Ciclos {

    // for: se usa cuando sabes cuántas veces quieres repetir.
    // Empieza en 1, repite mientras i sea menor o igual a 5 y en cada vuelta le suma 1 a i.
    public static void contarConFor() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Vuelta numero " + i);
        }
    }

    // while: repite mientras la condición sea verdadera.
    // Aquí imita 3 intentos de login: cuando intento llega a 4, se detiene.
    public static void intentosConWhile() {
        int intento = 1;
        while (intento <= 3) {
            System.out.println("Intento de login " + intento);
            intento++;
        }
    }

    // for-each: pasa por cada elemento de una lista, uno por uno.
    // Aquí saluda a cada nombre de la lista.
    public static void saludarConForEach() {
        List<String> nombres = List.of("Ana", "Luis", "Camila");
        for (String nombre : nombres) {
            System.out.println("Hola, " + nombre);
        }
    }
}
