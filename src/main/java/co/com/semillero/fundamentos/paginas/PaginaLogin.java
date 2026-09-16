package co.com.semillero.fundamentos.paginas;

/**
 * Página de inicio de sesión (simulada). Hereda de {@link PaginaBase}.
 *
 * <p>Así se ve un Page Object: los localizadores son constantes PRIVADAS y la clase ofrece métodos
 * con lenguaje de negocio ({@code iniciarSesion}, {@code obtenerMensaje}). Si mañana cambia el id
 * de un campo, solo se corrige aquí y ninguna prueba se rompe.</p>
 */
public class PaginaLogin extends PaginaBase {

    /** Localizador del campo usuario (en Selenium sería algo como By.id("usuario")). */
    private static final String CAMPO_USUARIO = "#usuario";

    /** Localizador del campo contraseña. */
    private static final String CAMPO_CLAVE = "#clave";

    /** Localizador del botón Ingresar. */
    private static final String BOTON_INGRESAR = "#ingresar";

    /** Localizador del mensaje que muestra la aplicación después de intentar ingresar. */
    private static final String MENSAJE = ".mensaje";

    /** Usuario válido del ambiente simulado. */
    public static final String USUARIO_VALIDO = "semillero";

    /** Contraseña válida del ambiente simulado (dato de prueba, no es un secreto real). */
    public static final String CLAVE_VALIDA = "Qa2026*";

    /** Mensaje que muestra la aplicación cuando el ingreso es correcto. */
    public static final String MENSAJE_BIENVENIDA = "Bienvenido, semillero";

    /** Mensaje que muestra la aplicación cuando las credenciales son incorrectas. */
    public static final String MENSAJE_ERROR = "Usuario o clave incorrectos";

    /** Crea la página de login. {@code super()} ejecuta primero el constructor de PaginaBase (si no se escribe, Java lo agrega solo). */
    public PaginaLogin() {
        super();
    }

    /**
     * Implementa el método abstracto del padre.
     *
     * @return URL simulada de la página de login
     */
    @Override
    public String getUrl() {
        return "https://semillero.qa/login";
    }

    /**
     * Acción de negocio: escribe usuario y clave, hace clic en Ingresar y deja el mensaje de respuesta.
     * Usa los métodos protegidos heredados ({@code escribir}, {@code hacerClic}).
     *
     * @param usuario nombre de usuario
     * @param clave   contraseña
     */
    public void iniciarSesion(String usuario, String clave) {
        escribir(CAMPO_USUARIO, usuario);
        escribir(CAMPO_CLAVE, clave);
        hacerClic(BOTON_INGRESAR);
        boolean credencialesValidas = USUARIO_VALIDO.equals(usuario)
                && CLAVE_VALIDA.equals(clave);
        mostrarTexto(MENSAJE, credencialesValidas ? MENSAJE_BIENVENIDA : MENSAJE_ERROR);
    }

    /**
     * Pregunta de negocio: qué mensaje muestra la página.
     *
     * @return el texto del mensaje
     */
    public String obtenerMensaje() {
        return leerTexto(MENSAJE);
    }
}
