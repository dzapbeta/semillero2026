# Clase 4: Playwright y Page Object Model (POM)

Semillero QA 2026, módulo Automatización Web. Profesor: Diego Zapata (Equipo Transversal QS).

| Clase | Entrega de la actividad | Retroalimentación |
|---|---|---|
| Martes 29 de septiembre de 2026, 9:00 a.m. – 12:00 m. (8:00–9:00 retro de la actividad 3) | Jueves 1 de octubre de 2026, 8:00 a.m. | Jueves 1 de octubre de 2026, 8:00 – 9:00 a.m. (y cierre del módulo 9:00–10:00) |

## Qué es este proyecto

Es un proyecto de pruebas automáticas sobre [saucedemo.com](https://www.saucedemo.com), una tienda de mentiras hecha para practicar. Automatiza los mismos flujos que viste en la clase 2 (login exitoso y usuario bloqueado), pero ahora con otra herramienta y otro patrón:

- **Playwright** es el framework que abre el navegador, escribe, hace clic y lee textos por ti.
- **POM (Page Object Model)** es la forma de ordenar el código: una clase por cada pantalla de la aplicación.

La idea es que al final de la clase puedas comparar POM con Screenplay y decir cuál usarías en cada caso.

## Requisitos

Necesitas JDK 21, Gradle y Git instalados en Windows. Todo el paso a paso está en `material-de-apoyo/Prerrequisitos_Instalacion_Entorno_Windows.docx`. Si ya hiciste las clases anteriores, ya lo tienes.

Versiones del proyecto: Playwright para Java 1.63.0, JUnit 5.14.4 y Gradle 9.7.1.

## Cómo se ejecuta en Windows

Abre PowerShell y sigue estos pasos.

1. Clona el repositorio (si no lo tienes) y cámbiate a esta rama:

   ```powershell
   git clone https://github.com/dzapbeta/semillero2026.git
   cd semillero2026
   git switch 04-playwright-pom
   ```

2. Instala el navegador de Playwright. Solo se hace una vez por equipo y descarga varios cientos de megas (en disco ocupa cerca de 560 MB), así que tenlo listo antes de la clase:

   ```powershell
   gradle instalarNavegadores
   ```

   Debe terminar con `BUILD SUCCESSFUL` y mostrar líneas que dicen `downloaded to ...ms-playwright\chromium-...`. Si ya estaba instalado, termina rápido sin descargar nada.

3. Corre las pruebas:

   ```powershell
   gradle test
   ```

   Debes ver algo así:

   ```
   LoginTest > Login exitoso con usuario estándar PASSED
   LoginTest > Login con usuario bloqueado muestra mensaje de error PASSED
   LoginTest > Login inválido muestra el mensaje correcto > Login inválido: usuario 'standard_user', clave 'clave_mala' PASSED
   LoginTest > Login inválido muestra el mensaje correcto > Login inválido: usuario '', clave 'secret_sauce' PASSED
   LoginTest > Login inválido muestra el mensaje correcto > Login inválido: usuario 'standard_user', clave '' PASSED

   BUILD SUCCESSFUL
   ```

Otros comandos útiles:

| Qué quieres hacer | Comando |
|---|---|
| Ver el navegador mientras corren las pruebas | `gradle test -Dheadless=false` |
| Correr solo una clase de pruebas | `gradle test --tests LoginTest` |
| Correr una sola prueba | `gradle test --tests LoginTest.loginExitoso` |

Por defecto las pruebas corren sin ventana (headless): el navegador trabaja por dentro y no ves nada, pero es más rápido. Cuando quieras ver qué hace, agrega `-Dheadless=false`. Va rápido, así que fíjate bien.

## Cómo funciona

### Playwright en pocas palabras

Playwright es una librería que controla un navegador desde código. Tú le dices "escribe esto en el campo Username" o "haz clic en Login" y él lo hace. Algo que te va a ahorrar dolores de cabeza: antes de hacer clic o de leer un texto, Playwright espera solo a que el elemento aparezca en la pantalla. Por eso en este proyecto no hay esperas escritas a mano. Playwright también puede grabar trazas de cada prueba, pero eso queda para después.

Para encontrar los elementos usamos localizadores. En `LoginPage` vas a ver tres formas:

- `getByPlaceholder("Username")`: busca el campo por el texto gris que tiene adentro.
- `getByRole(AriaRole.BUTTON, ...setName("Login"))`: busca un botón que dice Login, como lo buscaría una persona.
- `locator("[data-test='error']")`: busca por un atributo que los desarrolladores dejaron para pruebas.

### POM: un control remoto por pantalla

Piensa en el televisor de tu casa. Tú no abres el televisor para cambiar de canal: usas el control remoto, que tiene botones con nombres claros (subir volumen, canal siguiente). POM es lo mismo. Cada pantalla de la aplicación tiene su clase, y esa clase es el control remoto de la pantalla:

- Los localizadores son privados, como los cables dentro del televisor. La prueba no los ve.
- Los métodos públicos son los botones: `abrir()`, `iniciarSesion(usuario, clave)`, `mensajeDeError()`.

Si mañana cambia el HTML del botón Login, lo corriges en `LoginPage` y ninguna prueba se toca.

Así se ve una prueba:

```java
LoginPage login = new LoginPage(pagina);
login.abrir();
login.iniciarSesion("locked_out_user", "secret_sauce");

assertEquals("Epic sadface: Sorry, this user has been locked out.", login.mensajeDeError());
```

### El ciclo de vida de las pruebas

`BaseTest` es la clase padre de las pruebas. Se encarga del navegador para que `LoginTest` solo tenga pasos y validaciones:

1. `@BeforeAll`: antes de todas las pruebas arranca Playwright y abre Chromium (una sola vez, porque tarda).
2. `@BeforeEach`: antes de cada prueba abre una pestaña limpia.
3. Corre la prueba.
4. `@AfterEach`: cierra la pestaña.
5. `@AfterAll`: al final cierra el navegador y Playwright.

### Data driven con @CsvSource

La prueba `loginInvalido` se escribe una vez y JUnit la repite por cada fila de `@CsvSource`. Cada fila trae usuario, clave y el mensaje que debe salir. Por eso 3 pruebas escritas dan 5 resultados. `''` significa campo vacío.

### POM frente a Screenplay

| | Screenplay (clase 2) | POM (esta clase) |
|---|---|---|
| Quién hace las acciones | Un actor que ejecuta tareas (Task) | La página, con sus métodos |
| Dónde están los localizadores | En las clases de `userinterfaces` | Privados dentro de cada página |
| Cómo se valida | Con preguntas (Question) | La página devuelve un texto y la prueba lo compara con `assertEquals` |
| Cantidad de clases | Más clases, cada una pequeña | Pocas: una por pantalla |
| Cuándo conviene | Proyectos grandes con muchos flujos que se combinan | Proyectos pequeños o medianos, y para empezar |

No es que uno sea mejor. Cuando las páginas de POM crecen y se llenan de métodos, Screenplay ayuda a partir ese trabajo en piezas más pequeñas.

## Estructura de carpetas

```
04-playwright-pom/
├── build.gradle              configuración de Gradle: librerías, pruebas y la tarea instalarNavegadores
├── settings.gradle           nombre del proyecto
├── README.md                 este archivo
├── material-de-apoyo/        guía de estudio, presentación y actividad de la clase 4
└── src/
    ├── main/java/co/com/semillero/playwright/pages/
    │   ├── PaginaBase.java       clase padre de las páginas: guarda la pestaña y sabe navegar
    │   ├── LoginPage.java        control remoto de la pantalla de login
    │   └── ProductosPage.java    control remoto de la pantalla de productos
    └── test/java/co/com/semillero/playwright/tests/
        ├── BaseTest.java         abre y cierra el navegador
        └── LoginTest.java        2 pruebas de login y 1 prueba data driven con 3 filas
```

Las páginas van en `src/main` y las pruebas en `src/test`. Así queda separado el "cómo se maneja la aplicación" del "qué se está probando".

## Problemas comunes

### `Executable doesn't exist at ...ms-playwright\chromium_headless_shell-...`

No has instalado el navegador. Ejecuta `gradle instalarNavegadores` y vuelve a correr las pruebas. El mensaje de error te sugiere un comando con `mvn`: ignóralo, en este proyecto usamos Gradle.

### `unable to get local issuer certificate` o `UNABLE_TO_GET_ISSUER_CERT_LOCALLY` al instalar el navegador

Pasa en redes de empresa con proxy (por ejemplo Zscaler). La tarea `instalarNavegadores` ya trae la opción `NODE_OPTIONS=--use-system-ca`, que le dice a Playwright que use los certificados instalados en Windows.

Si instalas el navegador por otro camino (por ejemplo desde IntelliJ o con un comando de la documentación de Playwright), define la variable antes, en la misma ventana de PowerShell:

```powershell
$env:NODE_OPTIONS="--use-system-ca"
```

Si con `gradle instalarNavegadores` sigue saliendo el error, tu equipo no tiene instalado el certificado del proxy. Pídele ayuda al profesor.

### `strict mode violation: locator(...) resolved to 3 elements`

Tu localizador encontró varios elementos y Playwright no adivina cuál quieres. Hazlo más específico, por ejemplo buscando por `data-test` o dentro de un contenedor.

### `Timeout 30000ms exceeded` con `waiting for locator(...)`

Playwright esperó 30 segundos y nunca encontró el elemento. Casi siempre el localizador está mal escrito. Abre la página en Chrome, clic derecho, Inspeccionar, y revisa el atributo.

### El antivirus bloquea o borra archivos al correr las pruebas

Playwright copia un programa pequeño en la carpeta temporal de Windows cada vez que arranca y algunos antivirus lo frenan. Si ves errores raros de permisos o el primer intento tarda mucho, vuelve a correr. Si se repite, avísale al profesor.

### `gradle` no se reconoce como comando

Gradle no quedó en el PATH. Revisa la guía de prerrequisitos y abre una ventana nueva de PowerShell después de configurarlo.

## Material de apoyo

En la carpeta `material-de-apoyo/`:

- `Prerrequisitos_Instalacion_Entorno_Windows.docx`: instalación de Java, Gradle, Git, IntelliJ y Chrome, y del navegador de Playwright.
- `Clase4_Guia_Estudio_Playwright_POM.docx`: guía para estudiar antes y después de la clase.
- `Clase4_Presentacion_Playwright_POM.pptx`: presentación de la clase.
- `Clase4_Actividad_Playwright_POM.docx`: actividad, rúbrica y forma de entrega.

Documentación oficial: [Playwright para Java](https://playwright.dev/java/docs/intro) y [Page Object Models](https://playwright.dev/java/docs/pom).
