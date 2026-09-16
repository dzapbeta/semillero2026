package co.com.semillero.poo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Pruebas de la cuenta bancaria. Revisan que el saldo solo cambie como debe.
class CuentaBancariaTest {

    // Una cuenta recién creada no tiene dinero.
    @Test
    @DisplayName("Una cuenta nueva empieza con saldo 0")
    void cuentaNuevaEnCeroTest() {
        CuentaBancaria cuenta = new CuentaBancaria("Ana");
        assertEquals(0, cuenta.getSaldo());
    }

    // Depositar suma el valor al saldo.
    @Test
    @DisplayName("Depositar 50000 deja el saldo en 50000")
    void depositarSumaTest() {
        CuentaBancaria cuenta = new CuentaBancaria("Ana");
        cuenta.depositar(50000);
        assertEquals(50000, cuenta.getSaldo());
    }

    // Un depósito negativo no debe cambiar el saldo.
    @Test
    @DisplayName("Depositar un valor negativo no cambia el saldo")
    void depositoNegativoTest() {
        CuentaBancaria cuenta = new CuentaBancaria("Ana");
        cuenta.depositar(-1000);
        assertEquals(0, cuenta.getSaldo());
    }

    // Si hay saldo suficiente, el retiro se hace y se descuenta.
    @Test
    @DisplayName("Retirar con saldo suficiente descuenta el valor")
    void retiroConSaldoTest() {
        CuentaBancaria cuenta = new CuentaBancaria("Ana");
        cuenta.depositar(100000);
        assertTrue(cuenta.retirar(30000));
        assertEquals(70000, cuenta.getSaldo());
    }

    // Si no alcanza, el retiro se rechaza y el saldo nunca queda negativo.
    @Test
    @DisplayName("Retirar mas de lo que hay se rechaza y el saldo no queda negativo")
    void retiroSinSaldoTest() {
        CuentaBancaria cuenta = new CuentaBancaria("Ana");
        cuenta.depositar(10000);
        assertFalse(cuenta.retirar(20000));
        assertEquals(10000, cuenta.getSaldo());
    }
}
