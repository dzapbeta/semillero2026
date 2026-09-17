package co.com.semillero.control;

// Clase Condicionales: ejemplos de cómo el programa toma decisiones según un dato.
public class Condicionales {

    // Nota mínima para aprobar. final quiere decir que este valor nunca cambia.
    public static final double NOTA_MINIMA = 3.0;

    // Decide si una nota aprueba usando if/else.
    // Si la nota es 3.0 o más devuelve "Aprobado"; si no, devuelve "Reprobado".
    public static String evaluarNota(double nota) {
        if (nota >= NOTA_MINIMA) {
            return "Aprobado";
        } else {
            return "Reprobado";
        }
    }

    // Dice qué hacer según el color del semáforo usando switch.
    // switch compara el color con cada case; si ninguno coincide, usa default.
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
