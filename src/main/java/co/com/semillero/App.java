package co.com.semillero;

import co.com.semillero.control.Ciclos;
import co.com.semillero.control.Condicionales;
import co.com.semillero.herencia.Animal;
import co.com.semillero.herencia.Gato;
import co.com.semillero.herencia.Perro;
import co.com.semillero.poo.CuentaBancaria;
import co.com.semillero.poo.Estudiante;
import co.com.semillero.variables.Variables;

import java.util.List;

// Punto de entrada del proyecto. Llama a cada ejemplo en el mismo orden en que los vemos en clase.
public class App {

    // main es el método que Java ejecuta primero cuando corres "gradle run".
    public static void main(String[] args) {
        mostrarTitulo("1. Variables y tipos de datos");
        Variables.mostrarTiposDeDatos();
        mostrarTitulo("2. Condicionales (if/else y switch)");
        mostrarCondicionales();
        mostrarTitulo("3. Ciclos (for, while y for-each)");
        mostrarCiclos();
        mostrarTitulo("4. Clases y objetos");
        mostrarClasesYObjetos();
        mostrarTitulo("5. Encapsulamiento");
        mostrarEncapsulamiento();
        mostrarTitulo("6. Herencia y polimorfismo");
        mostrarPolimorfismo();
    }

    // Prueba el if/else con tres notas y el switch con tres colores, uno de ellos que no existe.
    private static void mostrarCondicionales() {
        System.out.println("Nota 4.2 -> " + Condicionales.evaluarNota(4.2));
        System.out.println("Nota 3.0 -> " + Condicionales.evaluarNota(3.0));
        System.out.println("Nota 2.5 -> " + Condicionales.evaluarNota(2.5));
        System.out.println("Semaforo verde -> " + Condicionales.accionSemaforo("verde"));
        System.out.println("Semaforo rojo -> " + Condicionales.accionSemaforo("rojo"));
        System.out.println("Semaforo azul -> " + Condicionales.accionSemaforo("azul"));
    }

    // Ejecuta los tres ciclos, cada uno con un subtítulo para saber cuál está imprimiendo.
    private static void mostrarCiclos() {
        System.out.println("-- for: contar de 1 a 5");
        Ciclos.contarConFor();
        System.out.println("-- while: tres intentos");
        Ciclos.intentosConWhile();
        System.out.println("-- for-each: recorrer una lista");
        Ciclos.saludarConForEach();
    }

    // Crea dos objetos con la misma clase Estudiante. Cada uno guarda sus propios datos.
    private static void mostrarClasesYObjetos() {
        Estudiante ana = new Estudiante("Ana", 4.5);
        Estudiante luis = new Estudiante("Luis", 2.8);
        System.out.println(ana.presentarse());
        System.out.println(luis.presentarse());
    }

    // Usa la cuenta solo a través de sus métodos. Los valores que no tienen sentido se rechazan.
    private static void mostrarEncapsulamiento() {
        CuentaBancaria cuenta = new CuentaBancaria("Ana");
        System.out.println("Cuenta nueva de " + cuenta.getTitular() + ", saldo: " + cuenta.getSaldo());
        cuenta.depositar(100000);
        System.out.println("Deposita 100000, saldo: " + cuenta.getSaldo());
        cuenta.depositar(-5000);
        System.out.println("Intenta depositar -5000, saldo: " + cuenta.getSaldo());
        System.out.println("Intenta retirar 500000, se pudo: " + cuenta.retirar(500000));
        System.out.println("Retira 30000, se pudo: " + cuenta.retirar(30000));
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }

    // Recorre una lista de Animal. Cada objeto responde con su propio sonido: eso es polimorfismo.
    private static void mostrarPolimorfismo() {
        List<Animal> animales = List.of(new Perro("Firulais"), new Gato("Michi"));
        for (Animal animal : animales) {
            System.out.println(animal.getNombre() + " dice " + animal.hacerSonido());
        }
    }

    // Imprime un título para separar cada ejemplo en la consola.
    private static void mostrarTitulo(String titulo) {
        System.out.println();
        System.out.println("===== " + titulo + " =====");
    }
}
