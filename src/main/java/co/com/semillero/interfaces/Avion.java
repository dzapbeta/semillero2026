package co.com.semillero.interfaces;

// Clase Avion: también implementa Volador, aunque no tiene nada que ver con un pájaro.
// Esa es la ventaja de la interfaz: junta clases distintas que saben hacer lo mismo.
public class Avion implements Volador {

    // Nombre o modelo del avión, por ejemplo "Boeing 737".
    private String modelo;

    // Constructor: guarda el modelo del avión.
    public Avion(String modelo) {
        this.modelo = modelo;
    }

    // Devuelve el modelo del avión.
    public String getModelo() {
        return modelo;
    }

    // El mismo método que pedía la interfaz, pero este lo hace a su manera.
    @Override
    public String volar() {
        return "vuela con sus turbinas";
    }
}
