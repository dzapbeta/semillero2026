package co.com.semillero.fundamentos;

import co.com.semillero.fundamentos.paginas.PaginaLogin;
import co.com.semillero.fundamentos.poo.encapsulamiento.CasoDePrueba;
import co.com.semillero.fundamentos.poo.encapsulamiento.Defecto;
import co.com.semillero.fundamentos.poo.encapsulamiento.Severidad;
import co.com.semillero.fundamentos.poo.herencia.Chrome;
import co.com.semillero.fundamentos.poo.herencia.Edge;
import co.com.semillero.fundamentos.poo.herencia.Firefox;
import co.com.semillero.fundamentos.poo.herencia.Navegador;
import co.com.semillero.fundamentos.poo.polimorfismo.EjecutorDePruebas;
import co.com.semillero.fundamentos.poo.polimorfismo.Ejecutable;
import co.com.semillero.fundamentos.poo.polimorfismo.PruebaApi;
import co.com.semillero.fundamentos.poo.polimorfismo.PruebaMovil;
import co.com.semillero.fundamentos.poo.polimorfismo.PruebaWeb;
import co.com.semillero.fundamentos.poo.polimorfismo.Reportero;
import co.com.semillero.fundamentos.poo.polimorfismo.ResumenEjecucion;
import co.com.semillero.fundamentos.variables.Colecciones;
import co.com.semillero.fundamentos.variables.EstructurasDeControl;
import co.com.semillero.fundamentos.variables.TiposDeDatos;

import java.util.List;

/**
 * Punto de entrada de la demostración de la clase 1.
 *
 * <p>Recorre los temas en el mismo orden de la clase e imprime el resultado en consola.
 * Se ejecuta con {@code mvn -q compile exec:java}.</p>
 */
public final class App {

    /** Constructor privado: esta clase solo existe para tener el método {@code main}. */
    private App() {
    }

    /**
     * Método {@code main}: Java empieza a ejecutar el programa por aquí.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        titulo("1. Variables y tipos de datos");
        TiposDeDatos.demostrar();

        titulo("1. Estructuras de control");
        EstructurasDeControl.demostrar();

        titulo("1. Arreglos y colecciones");
        Colecciones.demostrar();

        titulo("2. Clases, objetos y encapsulamiento");
        demostrarEncapsulamiento();

        titulo("3. Herencia");
        demostrarHerencia();

        titulo("3. Polimorfismo");
        demostrarPolimorfismo();

        titulo("Puente a automatización: Page Object");
        demostrarPaginaLogin();
    }

    /**
     * Crea objetos {@link CasoDePrueba} y {@link Defecto} y muestra cómo las validaciones protegen su estado.
     */
    private static void demostrarEncapsulamiento() {
        CasoDePrueba login = new CasoDePrueba("CP-001", "Login con credenciales válidas");
        login.agregarPaso("Abrir la página de login");
        login.agregarPaso("Escribir usuario y clave válidos");
        login.agregarPaso("Hacer clic en Ingresar");
        login.registrarEjecucion(true);
        System.out.println("Objeto creado con new: " + login);

        CasoDePrueba pago = new CasoDePrueba("CP-002", "Pagar con tarjeta");
        pago.agregarPaso("Agregar producto al carrito");
        pago.bloquear("Pasarela de pagos caída en QA");
        try {
            pago.registrarEjecucion(true);
        } catch (IllegalStateException e) {
            System.out.println("Validación del encapsulamiento: " + e.getMessage());
        }
        try {
            new CasoDePrueba("caso1", "Id con formato inválido");
        } catch (IllegalArgumentException e) {
            System.out.println("El constructor rechaza datos inválidos: " + e.getMessage());
        }

        Defecto defecto = new Defecto("BUG-101", "Botón Pagar no responde", Severidad.CRITICA, pago);
        System.out.println("Defecto asociado a un caso: " + defecto);
        System.out.println("¿Bloquea la salida a producción? " + defecto.bloqueaSalida());
    }

    /**
     * Crea navegadores hijos y muestra lo heredado, lo sobrescrito y el uso de {@code super}.
     */
    private static void demostrarHerencia() {
        List<Navegador> navegadores = List.of(new Chrome(false, true), new Firefox(true), new Edge(false));
        for (Navegador navegador : navegadores) {
            System.out.println(navegador.describir());
            System.out.println("  -> " + navegador.abrir("https://semillero.qa"));
        }
        try {
            new Edge(false).abrir("http://sitio-inseguro.qa");
        } catch (IllegalArgumentException e) {
            System.out.println("Edge sobrescribió la validación protegida: " + e.getMessage());
        }
    }

    /**
     * Ejecuta una lista mixta de pruebas (web, API, móvil) y muestra sobrecarga con {@link Reportero}.
     */
    private static void demostrarPolimorfismo() {
        List<Ejecutable> pruebas = List.of(
                new PruebaWeb("Login exitoso", new Chrome(true, false), "https://semillero.qa/login",
                        "Bienvenido", "Bienvenido"),
                new PruebaWeb("Buscar producto", new Firefox(true), "https://semillero.qa/buscar",
                        "3 resultados", "0 resultados"),
                new PruebaApi("Consultar usuario", "GET /usuarios/1", 200, 200),
                new PruebaApi("Crear usuario duplicado", "POST /usuarios", 409, 409),
                new PruebaMovil("Ver saldo", "Android 14 - Pixel 8", true));

        EjecutorDePruebas ejecutor = new EjecutorDePruebas();
        ResumenEjecucion resumen = ejecutor.ejecutarTodas(pruebas);
        ejecutor.getBitacora().forEach(linea -> System.out.println("  " + linea));

        Reportero reportero = new Reportero();
        System.out.println("Sobrecarga formatear(ResumenEjecucion): " + reportero.formatear(resumen));
        System.out.println("Sobrecarga formatear(String, boolean):  " + reportero.formatear("Ver saldo", true));
        CasoDePrueba caso = new CasoDePrueba("CP-010", "Buscar producto existente");
        System.out.println("Sobrecarga formatear(CasoDePrueba):     " + reportero.formatear(caso));
        Defecto defecto = new Defecto("BUG-102", "La búsqueda no devuelve resultados", Severidad.ALTA, caso);
        System.out.println("Sobrecarga formatear(Defecto):          " + reportero.formatear(defecto));
    }

    /**
     * Usa la página de login simulada como lo haría una prueba automatizada con POM.
     */
    private static void demostrarPaginaLogin() {
        PaginaLogin pagina = new PaginaLogin();
        System.out.println(pagina.abrir());
        pagina.iniciarSesion("semillero", "clave-mala");
        System.out.println("Intento con clave errada -> " + pagina.obtenerMensaje());
        pagina.iniciarSesion(PaginaLogin.USUARIO_VALIDO, PaginaLogin.CLAVE_VALIDA);
        System.out.println("Intento con credenciales válidas -> " + pagina.obtenerMensaje());
        System.out.println("Acciones registradas: " + pagina.getAcciones());
    }

    /**
     * Imprime un encabezado para separar cada sección de la demo.
     *
     * @param texto nombre de la sección
     */
    private static void titulo(String texto) {
        System.out.println();
        System.out.println("==== " + texto + " ====");
    }
}
