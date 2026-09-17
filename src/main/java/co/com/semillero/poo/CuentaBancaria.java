package co.com.semillero.poo;

// Clase CuentaBancaria: sirve para ver el encapsulamiento.
// El saldo está protegido: nadie lo cambia directamente, solo con depositar() y retirar().
public class CuentaBancaria {

    // Nombre del dueño de la cuenta.
    private String titular;

    // Dinero que hay en la cuenta. Es private para que nadie pueda escribir saldo = -500 desde afuera.
    private double saldo;

    // Constructor: crea la cuenta con el nombre del dueño. Toda cuenta nueva empieza con saldo 0.
    public CuentaBancaria(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }

    // Mete dinero a la cuenta. Si el valor es 0 o negativo, no hace nada.
    public void depositar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
        }
    }

    // Saca dinero solo si hay suficiente. Devuelve true si pudo sacarlo y false si no.
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo = saldo - valor;
            return true;
        }
        return false;
    }

    // Devuelve el saldo para poder verlo, pero no deja cambiarlo.
    public double getSaldo() {
        return saldo;
    }

    // Devuelve el nombre del dueño de la cuenta.
    public String getTitular() {
        return titular;
    }
}
