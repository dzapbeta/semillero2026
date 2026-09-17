package co.com.semillero.herencia;

// Clase Animal: es la clase padre.
// Guarda lo que tienen todos los animales (un nombre y un sonido) para no repetirlo en cada animal.
public class Animal {

    // Nombre del animal, por ejemplo "Firulais".
    // protected quiere decir: lo pueden usar esta clase y sus clases hijas (Perro y Gato).
    protected String nombre;

    // Constructor: se ejecuta cuando creas un animal con new y guarda su nombre.
    public Animal(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el nombre del animal.
    public String getNombre() {
        return nombre;
    }

    // Devuelve el sonido del animal. Aquí no sabemos qué animal es, por eso devuelve "...".
    // Cada clase hija lo cambia por su propio sonido.
    public String hacerSonido() {
        return "...";
    }
}
