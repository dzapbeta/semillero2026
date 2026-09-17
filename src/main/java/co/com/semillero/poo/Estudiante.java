package co.com.semillero.poo;

// Clase Estudiante: es el molde para crear estudiantes.
// Con este molde creamos objetos, por ejemplo Ana y Luis. Cada uno guarda sus propios datos.
public class Estudiante {

    // Nombre del estudiante. private quiere decir que solo esta clase lo puede cambiar.
    private String nombre;

    // Nota del estudiante, de 0.0 a 5.0.
    private double nota;

    // Constructor: se ejecuta cuando escribes new Estudiante("Ana", 4.5) y guarda el nombre y la nota.
    // this.nombre es el dato del objeto; nombre (sin this) es el valor que llega entre paréntesis.
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

    // Arma una frase con los datos del estudiante, por ejemplo "Soy Ana y mi nota es 4.5".
    public String presentarse() {
        return "Soy " + nombre + " y mi nota es " + nota;
    }
}
