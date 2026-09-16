package co.com.semillero.certificacion.dummyjson.utils;

/**
 * ENDPOINTS de la API DummyJSON (rutas relativas a la URL base definida en serenity.conf).
 *
 * ¿Por qué no hay paquete "userinterfaces" en un proyecto de API?
 * En Web, userinterfaces guarda los Target (DÓNDE está cada botón o campo de la página).
 * En API no hay pantalla: el "dónde" es la RUTA del recurso. Por eso esta clase cumple ese mismo papel.
 * Se deja en utils (y no en un paquete propio) porque son solo constantes, sin lógica, y así lo pide la
 * arquitectura del semillero: utils = constantes y utilidades compartidas.
 *
 * Es "final" y con constructor privado: nadie puede heredarla ni crear objetos; solo se usan sus constantes.
 */
public final class Endpoints {

    /** POST: inicia sesión con usuario y clave; devuelve accessToken. */
    public static final String INICIAR_SESION = "/auth/login";

    /** GET: devuelve el usuario dueño del token enviado en la cabecera Authorization. */
    public static final String PERFIL_AUTENTICADO = "/auth/me";

    /** GET: consulta un producto por id. {id} es un parámetro de ruta que se reemplaza al enviar la petición. */
    public static final String PRODUCTO_POR_ID = "/products/{id}";

    /** GET: busca productos. Se le agrega el parámetro de consulta ?q=texto. */
    public static final String BUSCAR_PRODUCTOS = "/products/search";

    /** POST: crea (simula crear) un producto. DummyJSON no lo guarda de verdad, pero responde como si lo hiciera. */
    public static final String CREAR_PRODUCTO = "/products/add";

    /** Constructor privado: evita que se creen objetos de una clase que solo tiene constantes. */
    private Endpoints() {
    }
}
