package co.com.semillero.certificacion.dummyjson.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

// Runner: la clase que arranca las pruebas. No tiene código, todo va en anotaciones.
// @Suite: es un grupo de pruebas.
// @IncludeEngines("cucumber"): las pruebas se escriben en archivos .feature.
// @SelectClasspathResource("features"): ejecuta todos los .feature de src/test/resources/features.
// El primer parámetro (plugin) manda cada paso al reporte de Serenity y lo imprime en consola.
// El segundo parámetro (glue) dice el paquete donde Cucumber busca los steps.
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,
        value = "co.com.semillero.certificacion.dummyjson.stepsdefinitions")
public class EjecutarPruebasTest {
}
