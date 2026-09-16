# Clase 2: Screenplay con Serenity BDD y Cucumber (Web)

Semillero QA 2026, módulo Automatización Web. Profesor: Diego Zapata (Equipo Transversal QS).

- Clase: miércoles 23 de septiembre de 2026, 9:00 a.m. a 12:00 m. (de 8:00 a 9:00 hacemos la retroalimentación de la actividad 1).
- Entrega de la actividad: viernes 25 de septiembre de 2026, 8:00 a.m.
- Retroalimentación: viernes 25 de septiembre de 2026, 8:00 a 9:00 a.m.

## Qué es este proyecto

Es una automatización pequeña de la tienda de práctica [Sauce Demo](https://www.saucedemo.com). Tiene dos escenarios de inicio de sesión:

1. Un usuario válido entra y ve el título "Products".
2. Un usuario bloqueado no entra y la página muestra el mensaje de error.

Con esos dos escenarios vemos los temas de la clase: qué es un framework de automatización (Selenium y Serenity BDD), cómo se escribe una prueba en lenguaje de negocio con Cucumber (BDD), qué es el patrón Screenplay y para qué sirve cada capa del proyecto.

Herramientas y versiones que usa:

| Herramienta | Versión |
|---|---|
| Java | 21 |
| Gradle | 9.7.1 (instalado en tu equipo, sin wrapper) |
| Serenity BDD | 5.3.11 |
| Plugin de reporte `net.serenity-bdd.serenity-gradle-plugin` | 5.3.9 (la última publicada) |
| Cucumber | 7.34.2 |
| JUnit Platform (el motor que ejecuta las pruebas en Java) | 6.0.3 |
| Selenium | 4.46.0 (lo trae Serenity) |

## Requisitos

Necesitas JDK 21 (Eclipse Temurin), Gradle 9.7.1, Git, Google Chrome e IntelliJ IDEA con los plugins "Cucumber for Java" y "Gherkin". La instalación paso a paso está en `material-de-apoyo/Prerrequisitos_Instalacion_Entorno_Windows.docx`.

También necesitas internet: la primera vez Gradle descarga las librerías y las pruebas abren saucedemo.com. No tienes que descargar el chromedriver; Selenium lo baja solo.

## Cómo se ejecuta en Windows

Abre PowerShell y sigue estos pasos.

1. Revisa que Java y Gradle respondan:

   ```powershell
   java -version
   gradle -v
   ```

2. Clona el repositorio y cámbiate a la rama de la clase:

   ```powershell
   git clone https://github.com/dzapbeta/semillero2026.git
   cd semillero2026
   git switch 02-screenplay-serenity-cucumber-web
   ```

3. Ejecuta los escenarios. En la clase 1 usabas `gradle run` para correr un programa; aquí usamos `gradle clean test`, que ejecuta pruebas. Se abre Chrome y vas a ver cómo escribe el usuario y la clave:

   ```powershell
   gradle clean test
   ```

   La primera vez tarda varios minutos porque descarga todo. Al final debes ver:

   ```
   SauceDemoRunner > Inicio de sesión en Sauce Demo > Inicio de sesión exitoso PASSED
   SauceDemoRunner > Inicio de sesión en Sauce Demo > Usuario bloqueado PASSED
   BUILD SUCCESSFUL
   ```

   `PASSED` quiere decir que el escenario pasó: lo que vio la prueba coincidió con lo esperado. Si algo no coincide, sale `FAILED` con el motivo.

4. Abre el reporte de Serenity:

   ```powershell
   start target\site\serenity\index.html
   ```

   `gradle clean test` ya genera el reporte al terminar, pasen o fallen las pruebas. Si prefieres escribirlo completo, `gradle clean test aggregate` hace lo mismo.

Otras formas de ejecutar:

```powershell
# Sin abrir la ventana de Chrome (modo headless)
gradle clean test "-Dheadless.mode=true"

# Solo un escenario, usando su tag
gradle clean test "-Dcucumber.filter.tags=@bloqueado"

# Las dos cosas juntas
gradle clean test "-Dheadless.mode=true" "-Dcucumber.filter.tags=@exitoso"
```

Escribe cada `-D...` entre comillas. En PowerShell, sin comillas, el punto del nombre puede partir el parámetro en dos y Gradle no lo recibe bien.

Los tags que hay son `@login` (los dos escenarios), `@exitoso` y `@bloqueado`.

## Cómo funciona

### La idea: una obra de teatro

Screenplay significa "guion". La forma más fácil de entenderlo es pensar en una obra de teatro:

- El **guion** es el archivo `login.feature`. Está en español y cualquier persona del equipo lo puede leer.
- La **actriz** es Ana. Es quien usa la página, igual que lo haría un usuario real.
- El **escenario** se prepara antes de cada función. Eso lo hace `Hooks`: le da a los actores la habilidad de usar el navegador.
- Lo que Ana **hace** son tareas (`IniciarSesion`), y cada tarea está hecha de movimientos pequeños (escribir, dar clic, abrir la página).
- Lo que Ana **mira** para saber si todo salió bien son preguntas (`TituloDeLaPagina`, `MensajeDeError`).
- Para no perderse, Ana tiene un **mapa** de dónde está cada cosa en la página (`PaginaLogin`, `PaginaProductos`).

### Recorrido de un paso, desde el feature hasta Chrome

Tomemos esta línea del feature:

```gherkin
Cuando inicia sesión con el usuario "standard_user" y la clave "secret_sauce"
```

1. `SauceDemoRunner` le dice a Cucumber que lea los `.feature` y que busque los steps en el paquete `stepsdefinitions`.
2. Cucumber encuentra el método que tiene la misma frase en `LoginSteps`:

   ```java
   @Cuando("inicia sesión con el usuario {string} y la clave {string}")
   public void iniciaSesion(String usuario, String clave) {
       theActorInTheSpotlight().attemptsTo(IniciarSesion.con(usuario, clave));
   }
   ```

   Cada `{string}` recibe lo que va entre comillas: `usuario` queda con `standard_user` y `clave` con `secret_sauce`.
3. Ana, la actriz que está en escena, intenta la tarea `IniciarSesion`.
4. La tarea hace tres acciones: escribe el usuario en `PaginaLogin.CAMPO_USUARIO`, escribe la clave en `PaginaLogin.CAMPO_CLAVE` y da clic en `PaginaLogin.BOTON_INGRESAR`.
5. `PaginaLogin` dice dónde está cada elemento, por ejemplo el campo de usuario es el que tiene `id="user-name"`.
6. Serenity le pasa la orden a Selenium y Selenium mueve Chrome. En la ventana ves cómo se escribe el usuario.

Luego viene `Entonces debería ver el título "Products"`: Ana hace la pregunta `TituloDeLaPagina`, Serenity compara la respuesta con "Products" y, si no coincide, la prueba falla con el mensaje de `Constantes.TITULO_DIFERENTE`.

## Estructura de carpetas

```
02-screenplay-serenity-cucumber-web
├── build.gradle           librerías, versión de Java y cómo se corren las pruebas
├── settings.gradle        nombre del proyecto y de dónde salen los plugins
├── gradle.properties      hace que cada ejecución use un Gradle nuevo (ver "Problemas comunes")
├── material-de-apoyo      guía de estudio, presentación y actividad
└── src
    ├── main/java/co/com/semillero/certificacion/saucedemo
    │   ├── userinterfaces   PaginaLogin, PaginaProductos: dónde está cada elemento
    │   ├── interactions     AbrirLaPagina: una acción pequeña hecha por nosotros
    │   ├── tasks            IniciarSesion: lo que el usuario quiere lograr
    │   ├── questions        TituloDeLaPagina, MensajeDeError: lo que el actor mira
    │   ├── exceptions       ErrorDeValidacion: el error propio cuando algo no coincide
    │   └── utils            Constantes: URL y mensajes de falla
    └── test
        ├── java/co/com/semillero/certificacion/saucedemo
        │   ├── runners          SauceDemoRunner: el botón de arranque
        │   └── stepsdefinitions LoginSteps (frases del feature) y Hooks (prepara el escenario)
        └── resources
            ├── features/login.feature   los escenarios en español
            ├── serenity.conf            navegador, modo headless y capturas
            └── junit-platform.properties ajuste de Cucumber
```

Una regla sencilla para ubicarte: los steps no tienen clics ni localizadores, solo le piden cosas al actor (tareas, interacciones y preguntas); las tareas se arman con acciones de Serenity como `Enter` y `Click`; los localizadores viven únicamente en `userinterfaces`. Si ves un `By.id(...)` dentro de un step, algo quedó en la capa equivocada.

Este proyecto no usa la capa `models`. En proyectos más grandes ahí se guardan objetos de datos, por ejemplo una clase `Usuario` con usuario y clave; es una capa de apoyo, no una capa del patrón.

Cuando Gradle termina aparecen dos carpetas que no se suben a Git: `build` (código compilado) y `target` (reporte de Serenity).

## Problemas comunes

**`gradle` no se reconoce como comando.** Gradle no quedó en el PATH. Revisa la guía de prerrequisitos y abre una PowerShell nueva después de cambiar las variables de entorno.

**Falla la descarga de librerías (`Could not resolve` o `PKIX path building failed`).** Suele ser el proxy o el antivirus de la red. Prueba desde otra red (por ejemplo, compartiendo datos del celular) y, si ahí funciona, avisa en clase para configurar el proxy en tu equipo.

**No abre Chrome o sale `SessionNotCreatedException`.** Actualiza Chrome y vuelve a ejecutar. Selenium necesita internet para bajar el driver la primera vez; si el proxy lo bloquea, pide ayuda en clase.

**`The step '...' is undefined`.** La frase del feature no es igual a la del step. Revisa tildes, comillas y espacios. Gradle te muestra el método sugerido que deberías crear.

**`expected: #TagLine, #FeatureLine ... got 'Caracter?stica'`.** El `.feature` se guardó con otra codificación. Guárdalo como UTF-8 (en IntelliJ, abajo a la derecha dice la codificación del archivo).

**`Expected condition failed: waiting for By.id: ... to be present`.** Serenity no encontró un elemento. Revisa el localizador en `userinterfaces` contra la página real (clic derecho, Inspeccionar).

**El reporte muestra nombres de otro proyecto.** Pasa cuando Gradle reutiliza un proceso que ya corrió otro proyecto con Serenity. Por eso `gradle.properties` tiene `org.gradle.daemon=false`; no lo borres. Al ejecutar vas a ver el aviso "a single-use Daemon process will be forked", es normal.

**En la consola salen caracteres raros en lugar de tildes.** Es solo la forma en que PowerShell muestra el texto. Las pruebas y el reporte no se afectan.

## Material de apoyo

En la carpeta `material-de-apoyo/`:

- `Clase2_Guia_Estudio_Screenplay_Web.docx`: explicación paso a paso para leer antes y después de clase.
- `Clase2_Presentacion_Screenplay_Web.pptx`: las diapositivas de la clase.
- `Clase2_Actividad_Screenplay_Web.docx`: la actividad que entregas el viernes 25 de septiembre de 2026 a las 8:00 a.m.
- `Prerrequisitos_Instalacion_Entorno_Windows.docx`: instalación de Java, Gradle, Git, Chrome e IntelliJ.
