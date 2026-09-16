package co.com.semillero.poo;

// Cuenta de ahorros sencilla. Sirve para ver el encapsulamiento: el saldo solo cambia por sus métodos.
public class CuentaBancaria {

    // Nombre del dueño de la cuenta.
    private String titular;
    // Guarda el saldo. Es privado para que nadie lo cambie sin pasar por depositar() o retirar().
    private double saldo;

    // Crea la cuenta con su titular. Toda cuenta nueva empieza en 0.
    public CuentaBancaria(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }

    // Suma dinero al saldo. Si el valor es 0 o negativo no hace nada.
    public void depositar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
        }
    }

    // Resta dinero solo si alcanza. Devuelve true si pudo retirar y false si no.
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo = saldo - valor;
            return true;
        }
        return false;
    }

    // Permite consultar el saldo, pero no cambiarlo.
    public double getSaldo() {
        return saldo;
    }

    // Permite consultar el titular.
    public String getTitular() {
        return titular;
    }
}
