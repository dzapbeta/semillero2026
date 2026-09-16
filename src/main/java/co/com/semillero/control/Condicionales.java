package co.com.semillero.control;

// Ejemplos de decisiones: el programa elige un camino según un dato.
public class Condicionales {

    // Nota mínima para aprobar. Es una constante: final significa que no cambia.
    public static final double NOTA_MINIMA = 3.0;

    // Usa if/else: si la nota es 3.0 o más devuelve "Aprobado"; si no, "Reprobado".
    public static String evaluarNota(double nota) {
        if (nota >= NOTA_MINIMA) {
            return "Aprobado";
        } else {
            return "Reprobado";
        }
    }

    // Usa switch: según el color del semáforo devuelve lo que debe hacer el conductor.
    public static String accionSemaforo(String color) {
        switch (color) {
            case "verde":
                return "Siga";
            case "amarillo":
                return "Despacio";
            case "rojo":
                return "Pare";
            default:
                return "Color desconocido";
        }
    }
}
