package co.com.semillero.polimorfismo;

// Clase Calculadora: sirve para ver la sobrecarga de métodos.
// Tiene tres métodos que se llaman igual (sumar), pero cada uno recibe datos distintos.
// Java escoge cuál usar mirando cuántos datos le mandas y de qué tipo son.
public class Calculadora {

    // sumar con dos números enteros.
    // Ejemplo: sumar(2, 3) devuelve 5.
    public int sumar(int a, int b) {
        return a + b;
    }

    // sumar con tres números enteros. Se llama igual que el anterior, pero recibe un dato más.
    // Ejemplo: sumar(2, 3, 4) devuelve 9.
    public int sumar(int a, int b, int c) {
        return a + b + c;
    }

    // sumar con dos números con decimales. Mismo nombre, pero el tipo de dato es double.
    // Ejemplo: sumar(2.5, 1.5) devuelve 4.0.
    public double sumar(double a, double b) {
        return a + b;
    }
}
