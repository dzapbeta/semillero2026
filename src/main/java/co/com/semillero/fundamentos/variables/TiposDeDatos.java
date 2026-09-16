package co.com.semillero.fundamentos.variables;

/**
 * Tema 1 · Variables y tipos de datos.
 *
 * <p>Una variable es una "caja con nombre" donde guardamos un dato. En Java cada caja tiene un
 * <b>tipo</b> que dice qué puede guardar: números enteros, decimales, verdadero/falso, texto...
 * Esta clase reúne ejemplos con datos típicos de QA (tiempos de espera, reintentos, nombres de casos).</p>
 *
 * <p>Los métodos {@code static} se pueden llamar sin crear un objeto: {@code TiposDeDatos.metodo()}.</p>
 */
public final class TiposDeDatos {

    /**
     * Constante: número máximo de reintentos de una prueba inestable.
     * {@code final} significa que su valor no puede cambiar después de asignarse;
     * por convención las constantes se escriben en MAYÚSCULAS_CON_GUION_BAJO.
     */
    public static final int MAX_REINTENTOS = 3;

    /** Constante: segundos que esperaría una prueba web antes de fallar por tiempo (timeout). */
    public static final long TIEMPO_ESPERA_SEGUNDOS = 30L;

    /** Constante de texto: prefijo obligatorio de los identificadores de casos de prueba. */
    public static final String PREFIJO_CASO = "CP-";

    /**
     * Constructor privado: esta clase solo tiene métodos {@code static} (utilidades),
     * así que no tiene sentido crear objetos de ella con {@code new}.
     */
    private TiposDeDatos() {
    }

    /**
     * Convierte milisegundos a segundos.
     * Ilustra la <b>conversión implícita</b> (widening): un {@code long} se convierte solo a {@code double}
     * al dividir entre {@code 1000.0}, y así no se pierden los decimales.
     *
     * @param milisegundos duración medida, por ejemplo 1500
     * @return la duración en segundos, por ejemplo 1.5
     */
    public static double milisegundosASegundos(long milisegundos) {
        return milisegundos / 1000.0;
    }

    /**
     * Quita los decimales de un porcentaje.
     * Ilustra el <b>casting explícito</b> (narrowing): {@code (int)} obliga a convertir un {@code double}
     * en {@code int} y descarta la parte decimal (no redondea: 99.9 queda en 99).
     *
     * @param porcentaje valor con decimales, por ejemplo 87.5
     * @return solo la parte entera, por ejemplo 87
     */
    public static int truncarPorcentaje(double porcentaje) {
        return (int) porcentaje;
    }

    /**
     * Normaliza el nombre de un caso para usarlo como nombre de archivo de evidencia.
     * Ilustra métodos útiles de {@code String}: {@code trim}, {@code toLowerCase}, {@code replace}.
     * Los {@code String} son inmutables: cada método devuelve un texto NUEVO.
     *
     * @param nombreCaso nombre libre, por ejemplo "  Login Exitoso "
     * @return nombre limpio, por ejemplo "login_exitoso"
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public static String normalizarNombreCaso(String nombreCaso) {
        if (nombreCaso == null || nombreCaso.isBlank()) {
            throw new IllegalArgumentException("El nombre del caso no puede estar vacío");
        }
        return nombreCaso.trim().toLowerCase().replace(" ", "_");
    }

    /**
     * Valida de forma sencilla un identificador de caso como "CP-001".
     * Ilustra {@code startsWith}, {@code length}, {@code substring} y operadores lógicos ({@code &&}).
     *
     * @param id identificador a revisar
     * @return {@code true} si empieza por "CP-" y termina en exactamente 3 dígitos
     */
    public static boolean esIdDeCasoValido(String id) {
        return id != null
                && id.startsWith(PREFIJO_CASO)
                && id.length() == PREFIJO_CASO.length() + 3
                && id.substring(PREFIJO_CASO.length()).chars().allMatch(Character::isDigit);
    }

    /**
     * Convierte un texto (por ejemplo leído de un archivo de datos) a número.
     * Ilustra las clases <b>envoltorio</b> (wrappers): {@code Integer} es la versión objeto de {@code int}.
     *
     * @param texto número escrito como texto, por ejemplo "200"
     * @return el número como {@code Integer}
     * @throws NumberFormatException si el texto no es un número (por ejemplo "abc")
     */
    public static Integer textoANumero(String texto) {
        return Integer.valueOf(texto.trim());
    }

    /**
     * Compara dos textos por su CONTENIDO.
     * Error clásico: usar {@code ==} con {@code String}, que compara si son el mismo objeto en memoria,
     * no si dicen lo mismo. Para comparar contenido siempre se usa {@code equals}.
     *
     * @param esperado texto que esperamos (por ejemplo, el mensaje correcto)
     * @param obtenido texto que obtuvimos (por ejemplo, el mensaje que muestra la página)
     * @return {@code true} si ambos textos tienen el mismo contenido
     */
    public static boolean mismoTexto(String esperado, String obtenido) {
        return esperado != null && esperado.equals(obtenido);
    }

    /**
     * Imprime en consola un recorrido por los tipos de datos y operadores.
     * Lo usa {@code App} durante la demostración en clase.
     */
    public static void demostrar() {
        // Tipos primitivos: guardan el valor directamente.
        byte prioridad = 1;                 // enteros muy pequeños (-128 a 127)
        short casosDelSprint = 250;         // enteros pequeños
        int casosEjecutados = 120;          // el entero de uso diario
        long duracionMs = 1_534L;           // enteros grandes (la L indica long)
        float tasaDeFallo = 0.05f;          // decimal de baja precisión (la f indica float)
        double porcentajeExito = 87.5;      // el decimal de uso diario
        char resultado = 'P';               // un solo carácter, con comillas simples
        boolean ambienteDisponible = true;  // verdadero o falso

        // var: Java deduce el tipo a partir del valor (aquí, String). Solo para variables locales.
        var navegador = "Chrome";

        System.out.println("byte prioridad = " + prioridad + " | short casosDelSprint = " + casosDelSprint);
        System.out.println("int casosEjecutados = " + casosEjecutados + " | long duracionMs = " + duracionMs);
        System.out.println("float tasaDeFallo = " + tasaDeFallo + " | double porcentajeExito = " + porcentajeExito);
        System.out.println("char resultado = " + resultado + " | boolean ambienteDisponible = " + ambienteDisponible);
        System.out.println("var navegador = " + navegador + " (Java deduce que es String)");

        // Operadores aritméticos, de comparación, lógicos y ternario.
        int fallidos = 15;
        int exitosos = casosEjecutados - fallidos;
        System.out.println("Exitosos (resta) = " + exitosos + " | ¿par? (módulo %) = " + (exitosos % 2 == 0));
        System.out.println("División entera 7 / 2 = " + (7 / 2) + " | división decimal 7 / 2.0 = " + (7 / 2.0));
        String veredicto = (fallidos == 0 && ambienteDisponible) ? "APROBADO" : "CON FALLOS";
        System.out.println("Ternario -> veredicto del ciclo: " + veredicto);

        // Conversiones y métodos de String.
        System.out.println("1534 ms = " + milisegundosASegundos(duracionMs) + " s (conversión implícita)");
        System.out.println("(int) 87.9 = " + truncarPorcentaje(87.9) + " (casting explícito, no redondea)");
        System.out.println("Nombre normalizado: " + normalizarNombreCaso("  Login Exitoso "));
        System.out.println("¿'CP-001' es un id válido? " + esIdDeCasoValido("CP-001")
                + " | ¿'CP-1'? " + esIdDeCasoValido("CP-1"));
        System.out.println("Texto \"200\" a Integer + 1 = " + (textoANumero("200") + 1));
        System.out.println("Constantes: MAX_REINTENTOS=" + MAX_REINTENTOS + ", TIEMPO_ESPERA_SEGUNDOS=" + TIEMPO_ESPERA_SEGUNDOS);
    }
}
