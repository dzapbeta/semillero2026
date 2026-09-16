package co.com.semillero.certificacion.dummyjson.utils;

// Guarda la dirección de la API y las rutas que usamos.
// En la rama web las direcciones de los botones vivían en userinterfaces; en API el equivalente son estas rutas.
public class Rutas {

    // Dirección base de la API. Todas las rutas de abajo se pegan a esta.
    public static final String URL_BASE = "https://dummyjson.com";

    // Ruta para iniciar sesión (se llama con POST).
    public static final String INICIAR_SESION = "/auth/login";

    // Ruta para consultar un producto (se llama con GET). {id} se reemplaza por el número del producto.
    public static final String PRODUCTO = "/products/{id}";

    // Ruta del archivo con los datos de los productos.
    public static final String ARCHIVO_PRODUCTOS = "src/test/resources/datos/productos.csv";

    // Constructor privado: esta clase solo guarda datos, no se crean objetos de ella.
    private Rutas() {
    }
}
