package co.com.semillero.herencia;

// Perro hereda de Animal con extends: ya tiene nombre y getNombre() sin volver a escribirlos.
public class Perro extends Animal {

    // Constructor: con super(nombre) le pasa el nombre al constructor de Animal.
    public Perro(String nombre) {
        super(nombre);
    }

    // Sobrescribe el sonido de Animal. @Override avisa que estamos cambiando un método del padre.
    @Override
    public String hacerSonido() {
        return "Guau";
    }
}
