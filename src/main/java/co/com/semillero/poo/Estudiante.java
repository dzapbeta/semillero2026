package co.com.semillero.poo;

// Una clase es el plano. Cada estudiante que creamos con new es un objeto hecho con ese plano.
public class Estudiante {

    // Nombre del estudiante. Cada objeto tiene el suyo.
    private String nombre;
    // Nota final del estudiante, de 0.0 a 5.0.
    private double nota;

    // Constructor: se ejecuta al hacer new Estudiante(...) y llena los datos del objeto.
    public Estudiante(String nombre, double nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    // Devuelve el nombre para que otras clases lo puedan leer.
    public String getNombre() {
        return nombre;
    }

    // Devuelve la nota para que otras clases la puedan leer.
    public double getNota() {
        return nota;
    }

    // Arma una frase con los datos del estudiante para mostrarla en consola.
    public String presentarse() {
        return "Soy " + nombre + " y mi nota es " + nota;
    }
}
