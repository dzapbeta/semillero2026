package co.com.semillero.interfaces;

// Interfaz Volador: es una lista de obligaciones, no una clase.
// No trae código hecho: solo dice qué método debe tener la clase que la use.
// La clase que escriba "implements Volador" queda obligada a escribir el método volar().
public interface Volador {

    // Toda clase que implemente Volador debe escribir este método.
    // Fíjate que aquí no hay llaves ni código: solo el nombre, lo que recibe y lo que devuelve.
    String volar();
}
