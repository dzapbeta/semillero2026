package co.com.semillero.certificacion.dummyjson.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * RUNNER: la clase que JUnit Platform ejecuta para correr los features de Cucumber con Serenity.
 * No tiene código dentro: todo se configura con anotaciones.
 * - @Suite: la clase es un conjunto de pruebas.
 * - @IncludeEngines("cucumber"): usa el motor de Cucumber (lee archivos .feature).
 * - @SelectPackages("features"): ejecuta TODOS los .feature de src/test/resources/features.
 * - PLUGIN: SerenityReporterParallel envía cada paso al reporte de Serenity; pretty imprime los pasos en consola.
 * - GLUE: paquete donde Cucumber busca los steps y hooks.
 * Para filtrar por tag no se cambia esta clase: mvn clean verify -Dcucumber.filter.tags="@login"
 * El nombre termina en "Test" porque así lo encuentra Failsafe (ver pom.xml).
 */
@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.com.semillero.certificacion.dummyjson.stepsdefinitions")
public class EjecutarPruebasApiTest {
}
