package co.com.semillero.herencia;

// Clase padre. Tiene lo que comparten todos los animales: un nombre y un sonido.
public class Animal {

    // Nombre del animal. Es protected para que las clases hijas también lo puedan usar.
    protected String nombre;

    // Constructor: recibe el nombre del animal.
    public Animal(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el nombre del animal.
    public String getNombre() {
        return nombre;
    }

    // Sonido genérico. Las clases hijas lo cambian por su propio sonido.
    public String hacerSonido() {
        return "...";
    }
}
