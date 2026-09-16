package co.com.semillero.herencia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Pruebas de herencia y polimorfismo con Animal, Perro y Gato.
class AnimalTest {

    // Perro heredó getNombre() de Animal y lo puede usar sin escribirlo otra vez.
    @Test
    @DisplayName("El perro hereda el nombre de Animal")
    void perroHeredaNombreTest() {
        Perro perro = new Perro("Firulais");
        assertEquals("Firulais", perro.getNombre());
    }

    // Perro sobrescribe hacerSonido() y responde con su propio sonido.
    @Test
    @DisplayName("El perro hace Guau")
    void perroHaceGuauTest() {
        assertEquals("Guau", new Perro("Firulais").hacerSonido());
    }

    // Gato sobrescribe hacerSonido() con otro sonido distinto.
    @Test
    @DisplayName("El gato hace Miau")
    void gatoHaceMiauTest() {
        assertEquals("Miau", new Gato("Michi").hacerSonido());
    }

    // Polimorfismo: la variable es de tipo Animal, pero cada objeto responde como lo que realmente es.
    @Test
    @DisplayName("En una lista de Animal cada uno hace su propio sonido")
    void polimorfismoTest() {
        List<Animal> animales = List.of(new Perro("Firulais"), new Gato("Michi"));
        assertEquals("Guau", animales.get(0).hacerSonido());
        assertEquals("Miau", animales.get(1).hacerSonido());
    }
}
