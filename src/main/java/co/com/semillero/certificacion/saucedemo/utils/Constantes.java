package co.com.semillero.certificacion.saucedemo.utils;

/**
 * Constantes del proyecto: valores fijos que se usan en varias clases.
 * Tenerlos en un solo lugar evita "textos mágicos" repetidos y facilita cambiarlos.
 */
public final class Constantes {

    /** Nombre de la propiedad de serenity.conf que guarda la URL de la aplicación. */
    public static final String PROPIEDAD_URL_BASE = "webdriver.base.url";

    /** Archivo (dentro de src/test/resources) con los usuarios de prueba. */
    public static final String ARCHIVO_USUARIOS = "datos/usuarios.properties";

    /** Segundos máximos que se espera a que un elemento aparezca en pantalla. */
    public static final int SEGUNDOS_DE_ESPERA = 10;

    /** Constructor privado: esta clase solo guarda constantes, no se crean objetos de ella. */
    private Constantes() {
    }
}
