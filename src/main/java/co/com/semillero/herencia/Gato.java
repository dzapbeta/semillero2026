package co.com.semillero.herencia;

// Clase Gato: también es hija de Animal, igual que Perro.
public class Gato extends Animal {

    // Constructor: recibe el nombre y se lo entrega a Animal con super(nombre).
    public Gato(String nombre) {
        super(nombre);
    }

    // Cambia el sonido que venía de Animal por el del gato.
    @Override
    public String hacerSonido() {
        return "Miau";
    }
}
