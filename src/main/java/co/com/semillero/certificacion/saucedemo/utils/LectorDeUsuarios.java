package co.com.semillero.certificacion.saucedemo.utils;

import co.com.semillero.certificacion.saucedemo.models.Usuario;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Utilidad que construye objetos Usuario a partir del archivo datos/usuarios.properties.
 * Así los features hablan de "usuario estandar" o "usuario bloqueado" (lenguaje de negocio)
 * y las credenciales reales quedan fuera de los features y del código.
 */
public final class LectorDeUsuarios {

    /** Constructor privado: solo se usan sus métodos estáticos. */
    private LectorDeUsuarios() {
    }

    /**
     * Busca en el archivo las claves "tipo.usuario" y "tipo.clave" y devuelve el Usuario.
     * Ejemplo: tipo = "estandar" lee "estandar.usuario" y "estandar.clave".
     * Si el tipo no existe, lanza un error claro en lugar de devolver datos vacíos.
     */
    public static Usuario deTipo(String tipo) {
        Properties propiedades = cargarArchivo();
        String nombre = propiedades.getProperty(tipo + ".usuario");
        String clave = propiedades.getProperty(tipo + ".clave");
        if (nombre == null || clave == null) {
            throw new IllegalArgumentException("No existe el tipo de usuario '" + tipo
                    + "' en " + Constantes.ARCHIVO_USUARIOS);
        }
        return new Usuario(nombre, clave);
    }

    /** Abre el archivo de usuarios desde el classpath y lo carga en un objeto Properties (UTF-8). */
    private static Properties cargarArchivo() {
        Properties propiedades = new Properties();
        try (InputStream archivo = LectorDeUsuarios.class.getClassLoader()
                .getResourceAsStream(Constantes.ARCHIVO_USUARIOS)) {
            if (archivo == null) {
                throw new IllegalStateException("No se encontró el archivo " + Constantes.ARCHIVO_USUARIOS);
            }
            propiedades.load(new InputStreamReader(archivo, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer " + Constantes.ARCHIVO_USUARIOS, e);
        }
        return propiedades;
    }
}
