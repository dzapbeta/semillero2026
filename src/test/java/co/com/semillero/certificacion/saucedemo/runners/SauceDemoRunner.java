package co.com.semillero.certificacion.saucedemo.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

// Runner: es el botón de arranque. Le dice a JUnit que ejecute los .feature con Cucumber.
// La clase va vacía a propósito; toda la configuración está en las anotaciones.
@Suite
// Usa el motor de Cucumber para ejecutar.
@IncludeEngines("cucumber")
// Busca los archivos .feature en la carpeta src/test/resources/features.
@SelectClasspathResource("features")
// Dónde están los steps y los hooks (el código que conecta cada frase con Java).
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.com.semillero.certificacion.saucedemo.stepsdefinitions")
// Guarda los resultados de cada escenario para armar el reporte de Serenity.
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel")
public class SauceDemoRunner {
}
