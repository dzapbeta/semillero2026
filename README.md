# Clase 3: Screenplay con Serenity BDD y Cucumber para API, con Data Driven

Semillero QA 2026, módulo Automatización Web. Profesor: Diego Zapata (Equipo Transversal QS).

Clase del viernes 25 de septiembre de 2026, de 9:00 a.m. a 12:00 m. (de 8:00 a 9:00 hacemos la retroalimentación de la actividad 2). La actividad se entrega el martes 29 de septiembre de 2026 a las 8:00 a.m.

## Qué es este proyecto

En la clase 2 automatizamos una página web con Screenplay. Aquí usamos el mismo patrón, pero en vez de abrir un navegador el actor le habla directamente a una API: envía una petición y revisa la respuesta.

La API es pública y gratuita: https://dummyjson.com. Probamos dos servicios:

- `POST /auth/login`: iniciar sesión con usuario y clave.
- `GET /products/{id}`: consultar un producto por su número.

El proyecto tiene 6 pruebas (escenarios) que salen de solo dos archivos `.feature`. Eso es lo que vamos a aprender: escribir el escenario una vez y ejecutarlo con varios datos.

## Requisitos

Antes de empezar necesitas tener instalado el JDK 21 (Eclipse Temurin), Gradle 9.7.1, Git e IntelliJ IDEA Community. La instalación paso a paso en Windows está en la guía `Prerrequisitos_Instalacion_Entorno_Windows.docx`, que está en la carpeta `material-de-apoyo/` de la rama `main` del repositorio.

También necesitas internet, porque las pruebas llaman a dummyjson.com.

Para comprobar que todo está listo, abre PowerShell y escribe:

```powershell
java -version
gradle -v
```

El primero debe decir `21` y el segundo `Gradle 9.7.1`.

## Cómo se ejecuta en Windows (PowerShell)

1. Clona el repositorio y cámbiate a la rama de esta clase:

   ```powershell
   git clone https://github.com/dzapbeta/semillero2026.git
   cd semillero2026
   git switch 03-screenplay-serenity-cucumber-api-datadriven
   ```

2. Ejecuta todas las pruebas:

   ```powershell
   gradle clean test
   ```

   La primera vez tarda varios minutos porque Gradle descarga las librerías. Al final debes ver 6 líneas que terminan en `PASSED` y el mensaje `BUILD SUCCESSFUL`.

3. Abre el reporte de Serenity. Ese mismo comando lo genera al terminar las pruebas:

   ```powershell
   start target\site\serenity\index.html
   ```

4. Si solo quieres ejecutar un archivo, filtra por su tag. Las comillas son necesarias en PowerShell:

   ```powershell
   gradle clean test "-Ptags=@login"
   gradle clean test "-Ptags=@productos"
   ```

   Los escenarios que no tienen ese tag salen como `SKIPPED`. Eso es normal.

En el reporte, entra a un escenario y, en Steps, despliega un paso hasta ver la línea `POST https://dummyjson.com/auth/login` (o `GET` en productos). Al lado está el botón verde **REST Query**. Ahí ves lo que se envió (la petición) y lo que respondió la API (código, cabeceras y cuerpo JSON). Es la mejor forma de entender qué está pasando.

## Cómo funciona

Piensa en una receta de cocina. La receta dice los pasos: pelar, picar, cocinar. Los ingredientes pueden cambiar: hoy papa, mañana yuca. La receta no se vuelve a escribir.

En este proyecto la receta es el **Esquema del escenario** y los ingredientes son las filas de **Ejemplos**. Mira `login.feature`:

```gherkin
Esquema del escenario: Iniciar sesión con <caso>
  Cuando el analista inicia sesión con el usuario "<usuario>" y la clave "<clave>"
  Entonces el código de respuesta debe ser <codigo>
  Y el campo "<campo>" de la respuesta debe ser "<valor>"

  Ejemplos:
    | caso             | usuario | clave      | codigo | campo    | valor                          |
    | datos correctos  | emilys  | emilyspass | 200    | username | emilys                         |
    | clave incorrecta | emilys  | clave123   | 400    | message  | Invalid credentials            |
    | campos en blanco |         |            | 400    | message  | Username and password required |
```

Cucumber cambia cada `<algo>` por el valor de la fila y ejecuta el escenario 3 veces.

`productos.feature` da un paso más: los datos esperados no están en el feature sino en un archivo aparte, `src/test/resources/datos/productos.csv`. En Ejemplos solo va el id del producto. El step le pide a `LectorCsv` el título que corresponde a ese id y lo compara con el campo `title` de la respuesta. Eso es **Data Driven**: los datos viven fuera del código y, si quieres un caso nuevo, agregas una fila al CSV y otra en Ejemplos sin tocar Java.

El recorrido de una prueba es siempre el mismo:

1. El feature dice qué se prueba, en español.
2. El step definition traduce cada frase a una orden para el actor.
3. El actor ejecuta una tarea (`IniciarSesion` o `ConsultarProducto`), que hace un `Post` o un `Get`.
4. El actor hace una pregunta (`CodigoDeRespuesta` o `CampoDeLaRespuesta`) y compara la respuesta con lo esperado.
5. Si no coincide, sale el error propio `RespuestaInesperada` con un mensaje en español.

## Estructura de carpetas

```
build.gradle, settings.gradle          configuración de Gradle (librerías y cómo se ejecutan las pruebas)
src/main/java/co/com/semillero/certificacion/dummyjson/
  tasks/         IniciarSesion, ConsultarProducto: lo que hace el actor
  questions/     CodigoDeRespuesta, CampoDeLaRespuesta: lo que el actor revisa
  exceptions/    RespuestaInesperada: el error propio con mensaje claro
  models/        Credenciales: el cuerpo JSON que se envía al iniciar sesión
  utils/         Rutas (dirección de la API y rutas), LectorCsv (lee el archivo de datos)
src/test/java/co/com/semillero/certificacion/dummyjson/
  runners/           EjecutarPruebasTest: arranca Cucumber
  stepsdefinitions/  Hooks, LoginStepDefinitions, ProductosStepDefinitions
src/test/resources/
  features/      login.feature, productos.feature
  datos/         productos.csv
  serenity.conf  nombre del proyecto en el reporte
material-de-apoyo/   guía de estudio, presentación y actividad de la clase
```

Si comparas con la rama web vas a encontrar las mismas capas, con dos diferencias:

- **No hay `userinterfaces`.** En web esa carpeta guardaba dónde estaban los botones y campos de la página. En una API no hay pantalla: lo equivalente son las rutas (`/auth/login`, `/products/{id}`), y esas están en `utils/Rutas.java`.
- **No hay `interactions` propias.** Las interacciones que usamos ya vienen con Serenity: `Post` y `Get` (paquete `net.serenitybdd.screenplay.rest.interactions`). No hizo falta crear una.

La habilidad del actor también cambia: en web era `BrowseTheWeb` (manejar un navegador) y aquí es `CallAnApi` (llamar a una API). Se la damos en `Hooks.java`.

## Versiones

- Serenity BDD 5.3.11 (`serenity-core`, `serenity-screenplay-rest`, `serenity-cucumber`)
- Cucumber 7.34.2 con JUnit Platform (JUnit 6.0.3, alineado con `junit-bom`)
- Plugin de reporte `net.serenity-bdd.serenity-gradle-plugin` 5.3.9 (la última publicada en el portal de plugins de Gradle; funciona bien con Serenity 5.3.11)
- Gradle 9.7.1 y JDK 21

## Problemas comunes

**Se queda descargando o falla con `PKIX path building failed` o `Connect timed out`.** Casi siempre es el proxy o la VPN de la empresa. Prueba en otra red o pide al equipo de soporte que configure el proxy para Java y Gradle. Para saber si la API responde, abre https://dummyjson.com/products/1 en el navegador.

**`No encontré el id 1 en el archivo ...`.** Abriste el CSV con Excel y lo guardaste. En Windows en español, Excel guarda con punto y coma (`1;Essence...`) en vez de coma. Abre el archivo con el Bloc de notas o IntelliJ y deja las comas.

**Las tildes salen raras (`sesiÃ³n`).** El archivo no quedó en UTF-8. En IntelliJ, abajo a la derecha, cambia la codificación a UTF-8.

**`Expected: "..." but: was null`.** El nombre del campo está mal escrito. Los nombres del JSON distinguen mayúsculas: es `firstName`, no `firstname`. Míralo en el reporte, en Response Body.

**`gradle` no se reconoce como comando.** Falta la variable de entorno PATH. Revisa la guía de prerrequisitos.

**Todos los escenarios salen `SKIPPED`.** El tag está mal escrito o faltan las comillas en PowerShell. Debe ser `"-Ptags=@login"`, con la arroba.

## Material de apoyo

En la carpeta `material-de-apoyo/`:

- `Clase3_Guia_Estudio_Screenplay_API_DataDriven.docx`: la guía para leer antes y después de clase.
- `Clase3_Presentacion_Screenplay_API_DataDriven.pptx`: las diapositivas de la clase.
- `Clase3_Actividad_Screenplay_API_DataDriven.docx`: la actividad que entregas el martes 29 de septiembre de 2026 a las 8:00 a.m.
