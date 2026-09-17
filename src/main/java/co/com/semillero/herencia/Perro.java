package co.com.semillero.herencia;

// Clase Perro: es hija de Animal.
// extends Animal quiere decir que Perro recibe todo lo de Animal (nombre y getNombre) sin escribirlo otra vez.
public class Perro extends Animal {

    // Constructor: recibe el nombre y se lo entrega a Animal con super(nombre) para que lo guarde.
    public Perro(String nombre) {
        super(nombre);
    }

    // Cambia el sonido que venía de Animal por el del perro.
    // @Override avisa que este método reemplaza al del padre.
    @Override
    public String hacerSonido() {
        return "Guau";
    }
}
