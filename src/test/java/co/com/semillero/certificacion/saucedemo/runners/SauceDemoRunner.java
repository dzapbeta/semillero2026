package co.com.semillero.certificacion.saucedemo.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * RUNNER: punto de entrada de la ejecución. Es la forma que recomienda Serenity 5
 * (JUnit Platform + motor de Cucumber). La clase está vacía a propósito: todo se configura
 * con anotaciones. Failsafe la ejecuta porque su nombre termina en "Runner".
 */
// @Suite: le dice a JUnit Platform que esta clase es una suite de pruebas.
@Suite
// @IncludeEngines("cucumber"): la suite se ejecuta con el motor de Cucumber.
@IncludeEngines("cucumber")
// @SelectPackages("features"): ejecuta todos los .feature de la carpeta src/test/resources/features.
@SelectPackages("features")
// GLUE: paquete donde Cucumber busca los step definitions y los hooks.
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,
        value = "co.com.semillero.certificacion.saucedemo.stepsdefinitions")
// PLUGIN: SerenityReporterParallel guarda los resultados para el reporte; pretty imprime los pasos en consola.
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty")
public class SauceDemoRunner {
}
