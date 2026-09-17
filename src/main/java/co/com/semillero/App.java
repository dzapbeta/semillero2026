package co.com.semillero;

import co.com.semillero.control.Ciclos;
import co.com.semillero.control.Condicionales;
import co.com.semillero.herencia.Gato;
import co.com.semillero.herencia.Perro;
import co.com.semillero.polimorfismo.Calculadora;
import co.com.semillero.poo.CuentaBancaria;
import co.com.semillero.poo.Estudiante;
import co.com.semillero.variables.Variables;

// Clase App: es la que arranca el programa.
// Ejecuta los ejemplos en el mismo orden en que los vemos en clase.
public class App {

    // main es lo primero que Java ejecuta cuando escribes "gradle run".
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
        mostrarTitulo("6. Herencia");
        mostrarHerencia();
        mostrarTitulo("7. Polimorfismo: metodos sobrecargados");
        mostrarSobrecarga();
    }

    // Prueba el if/else con tres notas y el switch con tres colores (el azul no existe en el semáforo).
    private static void mostrarCondicionales() {
        System.out.println("Nota 4.2 -> " + Condicionales.evaluarNota(4.2));
        System.out.println("Nota 3.0 -> " + Condicionales.evaluarNota(3.0));
        System.out.println("Nota 2.5 -> " + Condicionales.evaluarNota(2.5));
        System.out.println("Semaforo verde -> " + Condicionales.accionSemaforo("verde"));
        System.out.println("Semaforo rojo -> " + Condicionales.accionSemaforo("rojo"));
        System.out.println("Semaforo azul -> " + Condicionales.accionSemaforo("azul"));
    }

    // Ejecuta los tres ciclos. Antes de cada uno imprime una línea para saber cuál está corriendo.
    private static void mostrarCiclos() {
        System.out.println("-- for: contar de 1 a 5");
        Ciclos.contarConFor();
        System.out.println("-- while: tres intentos");
        Ciclos.intentosConWhile();
        System.out.println("-- for-each: recorrer una lista");
        Ciclos.saludarConForEach();
    }

    // Crea dos estudiantes con el mismo molde (la clase Estudiante). Cada uno tiene sus propios datos.
    private static void mostrarClasesYObjetos() {
        Estudiante ana = new Estudiante("Ana", 4.5);
        Estudiante luis = new Estudiante("Luis", 2.8);
        System.out.println(ana.presentarse());
        System.out.println(luis.presentarse());
    }

    // Usa la cuenta solo con sus métodos. Los valores que no tienen sentido se rechazan.
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

    // Crea un perro y un gato. Los dos usan getNombre() que heredaron de Animal,
    // pero cada uno hace su propio sonido.
    private static void mostrarHerencia() {
        Perro perro = new Perro("Firulais");
        Gato gato = new Gato("Michi");
        System.out.println(perro.getNombre() + " dice " + perro.hacerSonido());
        System.out.println(gato.getNombre() + " dice " + gato.hacerSonido());
    }

    // Llama tres veces a sumar con datos distintos. Java escoge solo el método que corresponde.
    private static void mostrarSobrecarga() {
        Calculadora calculadora = new Calculadora();
        System.out.println("sumar(2, 3) = " + calculadora.sumar(2, 3));
        System.out.println("sumar(2, 3, 4) = " + calculadora.sumar(2, 3, 4));
        System.out.println("sumar(2.5, 1.5) = " + calculadora.sumar(2.5, 1.5));
    }

    // Imprime una línea en blanco y un título para separar cada ejemplo en la consola.
    private static void mostrarTitulo(String titulo) {
        System.out.println();
        System.out.println("===== " + titulo + " =====");
    }
}
