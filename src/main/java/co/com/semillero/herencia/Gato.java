package co.com.semillero.herencia;

// Gato también hereda de Animal, igual que Perro.
public class Gato extends Animal {

    // Constructor: le pasa el nombre a Animal.
    public Gato(String nombre) {
        super(nombre);
    }

    // Sobrescribe el sonido con el del gato.
    @Override
    public String hacerSonido() {
        return "Miau";
    }
}
