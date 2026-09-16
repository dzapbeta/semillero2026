package co.com.semillero.variables;

// Muestra los tipos de datos básicos de Java con ejemplos de la vida diaria.
public class Variables {

    // Crea una variable de cada tipo, la imprime y hace una suma y una concatenación.
    public static void mostrarTiposDeDatos() {
        // int guarda números enteros, sin decimales.
        int edad = 20;
        // double guarda números con decimales.
        double precio = 15500.50;
        // boolean solo puede ser true (verdadero) o false (falso).
        boolean estaActivo = true;
        // char guarda una sola letra y va entre comillas simples.
        char inicial = 'D';
        // String guarda un texto y va entre comillas dobles.
        String nombre = "Diego";

        System.out.println("int (numero entero): " + edad);
        System.out.println("double (numero con decimales): " + precio);
        System.out.println("boolean (verdadero o falso): " + estaActivo);
        System.out.println("char (una letra): " + inicial);
        System.out.println("String (texto): " + nombre);

        // Con números, el + suma.
        int edadEnCincoAnios = edad + 5;
        System.out.println("Suma: 20 + 5 = " + edadEnCincoAnios);

        // Con textos, el + pega un texto con otro. A eso se le llama concatenar.
        String saludo = "Hola, " + nombre;
        System.out.println("Concatenacion: " + saludo);
    }
}
