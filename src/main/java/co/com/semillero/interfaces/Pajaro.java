package co.com.semillero.interfaces;

// Clase Pajaro: usa la interfaz con "implements Volador".
// implements es un compromiso: prometo tener el método volar(). Si lo borras, el programa no compila.
public class Pajaro implements Volador {

    // Nombre del pájaro, por ejemplo "Gorrión".
    private String nombre;

    // Constructor: guarda el nombre del pájaro.
    public Pajaro(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el nombre del pájaro.
    public String getNombre() {
        return nombre;
    }

    // Este es el método que pedía la interfaz. Aquí sí escribimos qué hace.
    @Override
    public String volar() {
        return "vuela moviendo las alas";
    }
}
