package co.com.semillero.certificacion.dummyjson.utils;

/**
 * Nombres de las "notas" que el actor guarda en su memoria con actor.remember(...) y lee con actor.recall(...).
 * Tenerlos como constantes evita errores de tipeo (si se escribe "tokne" en un lado, la prueba falla sin razón clara).
 */
public final class MemoriaDelActor {

    /** Token de acceso obtenido al iniciar sesión; lo usa la interacción ConsultarConToken. */
    public static final String TOKEN_DE_ACCESO = "token de acceso";

    /** Fila del CSV que se está probando (ProductoEsperado). */
    public static final String PRODUCTO_ESPERADO = "producto esperado";

    /** Producto que se envió en el POST de creación (sale del DataTable). */
    public static final String PRODUCTO_ENVIADO = "producto enviado";

    /** Constructor privado: clase solo de constantes. */
    private MemoriaDelActor() {
    }
}
