package co.com.semillero.fundamentos.variables;

/**
 * Tema 1 · Estructuras de control.
 *
 * <p>Las estructuras de control deciden <b>qué</b> se ejecuta ({@code if}, {@code switch}) y
 * <b>cuántas veces</b> ({@code for}, {@code while}, {@code do-while}). Son las mismas decisiones que
 * toma un tester: "si el impacto es alto, la severidad es crítica", "reintenta hasta 3 veces".</p>
 */
public final class EstructurasDeControl {

    /** Constructor privado: clase de utilidades, solo tiene métodos {@code static}. */
    private EstructurasDeControl() {
    }

    /**
     * Clasifica la severidad de un defecto según su impacto (1 a 10).
     * Ilustra {@code if / else if / else} y el lanzamiento de una excepción cuando el dato es inválido.
     *
     * @param impacto número del 1 (menor) al 10 (mayor)
     * @return "CRITICA", "ALTA", "MEDIA" o "BAJA"
     * @throws IllegalArgumentException si el impacto está fuera del rango 1..10
     */
    public static String clasificarSeveridad(int impacto) {
        if (impacto < 1 || impacto > 10) {
            throw new IllegalArgumentException(
                    "El impacto debe estar entre 1 y 10 y llegó: " + impacto);
        } else if (impacto >= 9) {
            return "CRITICA";
        } else if (impacto >= 7) {
            return "ALTA";
        } else if (impacto >= 4) {
            return "MEDIA";
        } else {
            return "BAJA";
        }
    }

    /**
     * Traduce un código corto de resultado a una descripción.
     * Ilustra la <b>switch expression</b> (Java 14+): cada caso devuelve un valor con {@code ->},
     * sin necesidad de {@code break}, y {@code default} cubre cualquier otro valor.
     *
     * @param codigo "P" (pasó), "F" (falló), "B" (bloqueado) o "S" (saltado)
     * @return la descripción legible del resultado
     */
    public static String describirResultado(String codigo) {
        return switch (codigo) {
            case "P" -> "Pasó";
            case "F" -> "Falló";
            case "B" -> "Bloqueado";
            case "S", "O" -> "Saltado u omitido";
            default -> "Código desconocido: " + codigo;
        };
    }

    /**
     * Calcula el porcentaje de casos exitosos de una ejecución.
     *
     * @param exitosos cantidad de casos que pasaron
     * @param total    cantidad de casos ejecutados
     * @return porcentaje entre 0 y 100; si no se ejecutó nada ({@code total == 0}) devuelve 0
     * @throws IllegalArgumentException si hay números negativos o más exitosos que total
     */
    public static double calcularPorcentajeExito(int exitosos, int total) {
        if (exitosos < 0 || total < 0 || exitosos > total) {
            throw new IllegalArgumentException("Datos inválidos: exitosos=" + exitosos + ", total=" + total);
        }
        if (total == 0) {
            return 0.0;
        }
        return exitosos * 100.0 / total;
    }

    /**
     * Cuenta cuántas ejecuciones fallaron.
     * Ilustra el <b>for-each</b>: recorre cada elemento del arreglo sin manejar índices.
     *
     * @param resultados arreglo donde {@code true} = pasó y {@code false} = falló
     * @return cantidad de valores {@code false}
     */
    public static int contarFallidos(boolean[] resultados) {
        int fallidos = 0;
        for (boolean paso : resultados) {
            if (!paso) {
                fallidos++;
            }
        }
        return fallidos;
    }

    /**
     * Busca el primer caso que falló.
     * Ilustra el {@code for} clásico con índice y {@code break} para salir del ciclo apenas se encuentra.
     *
     * @param nombres    nombres de los casos, en el mismo orden que {@code resultados}
     * @param resultados {@code true} = pasó, {@code false} = falló
     * @return el nombre del primer caso fallido, o "Ninguno" si todos pasaron
     */
    public static String buscarPrimerFallido(String[] nombres, boolean[] resultados) {
        String primerFallido = "Ninguno";
        for (int i = 0; i < nombres.length; i++) {
            if (!resultados[i]) {
                primerFallido = nombres[i];
                break; // ya lo encontramos: no tiene sentido seguir recorriendo
            }
        }
        return primerFallido;
    }

    /**
     * Cuenta los casos que realmente se ejecutaron, ignorando los bloqueados.
     * Ilustra {@code continue}: salta a la siguiente vuelta del ciclo sin ejecutar el resto.
     *
     * @param codigos códigos de resultado ("P", "F", "B"...)
     * @return cantidad de casos distintos de "B"
     */
    public static int contarEjecutadosSinBloqueados(String[] codigos) {
        int ejecutados = 0;
        for (String codigo : codigos) {
            if ("B".equals(codigo)) {
                continue; // bloqueado: no cuenta como ejecutado
            }
            ejecutados++;
        }
        return ejecutados;
    }

    /**
     * Simula reintentar una prueba inestable ("flaky").
     * Ilustra {@code while}: repite MIENTRAS la condición sea verdadera (puede no ejecutarse nunca).
     *
     * @param fallosAntesDeExito cuántas veces falla la prueba antes de pasar
     * @param maxIntentos        máximo de intentos permitidos
     * @return el número del intento en que pasó, o -1 si se agotaron los intentos
     */
    public static int ejecutarConReintentos(int fallosAntesDeExito, int maxIntentos) {
        int intento = 1;
        while (intento <= maxIntentos) {
            boolean paso = intento > fallosAntesDeExito;
            if (paso) {
                return intento;
            }
            intento++;
        }
        return -1;
    }

    /**
     * Simula esperar a que una página termine de cargar, revisando por ciclos.
     * Ilustra {@code do-while}: el cuerpo se ejecuta AL MENOS UNA VEZ y luego evalúa la condición
     * (siempre hay que mirar la página por lo menos una vez).
     *
     * @param ciclosHastaCargar cuántas revisiones tarda la página en estar lista (0 = ya estaba lista)
     * @return cuántas revisiones se hicieron (mínimo 1)
     */
    public static int esperarCargaDePagina(int ciclosHastaCargar) {
        int revisiones = 0;
        boolean cargada;
        do {
            revisiones++;
            cargada = revisiones >= ciclosHastaCargar;
        } while (!cargada);
        return revisiones;
    }

    /**
     * Imprime en consola ejemplos de cada estructura de control. Lo usa {@code App}.
     */
    public static void demostrar() {
        System.out.println("if/else -> impacto 9: " + clasificarSeveridad(9) + " | impacto 5: " + clasificarSeveridad(5));
        System.out.println("switch  -> 'F': " + describirResultado("F") + " | 'X': " + describirResultado("X"));
        System.out.println("Porcentaje de éxito 45 de 50 = " + calcularPorcentajeExito(45, 50) + " %");
        boolean[] resultados = {true, true, false, true, false};
        String[] nombres = {"Login", "Buscar", "Pagar", "Salir", "Perfil"};
        System.out.println("for-each -> fallidos: " + contarFallidos(resultados));
        System.out.println("for + break -> primer fallido: " + buscarPrimerFallido(nombres, resultados));
        System.out.println("continue -> ejecutados sin bloqueados: "
                + contarEjecutadosSinBloqueados(new String[] {"P", "B", "F", "B", "P"}));
        System.out.println("while -> prueba flaky pasó en el intento: "
                + ejecutarConReintentos(2, TiposDeDatos.MAX_REINTENTOS));
        System.out.println("do-while -> revisiones hasta cargar: " + esperarCargaDePagina(0)
                + " (aunque ya estaba cargada, revisa una vez)");
    }
}
