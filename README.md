# Clase 4: Playwright con TypeScript y Page Object Model (POM)

Semillero QA 2026, módulo Automatización Web. Profesor: Diego Zapata (Equipo Transversal QS).

| Clase | Entrega de la actividad | Retroalimentación |
|---|---|---|
| Martes 29 de septiembre de 2026, 9:00 a.m. – 12:00 m. (8:00–9:00 retro de la actividad 3) | Jueves 1 de octubre de 2026, 8:00 a.m. | Jueves 1 de octubre de 2026, 8:00 – 9:00 a.m. (y cierre del módulo 9:00–10:00) |

## Qué es este proyecto

Es un proyecto de pruebas automáticas sobre [saucedemo.com](https://www.saucedemo.com), una tienda de mentiras hecha para practicar. Automatiza los mismos flujos que viste en la clase 2 (login exitoso y usuario bloqueado), pero con otra herramienta, otro lenguaje y otro patrón:

- Playwright es el framework que abre el navegador, escribe, hace clic y revisa lo que aparece en la pantalla.
- TypeScript es el lenguaje nativo de Playwright. Se parece a Java en muchas cosas, y abajo te cuento las diferencias.
- POM (Page Object Model) es la forma de ordenar el código: una clase por cada pantalla de la aplicación.
- VS Code es el editor. Con la extensión de Playwright corres y depuras las pruebas con un clic.

La idea es que al final de la clase puedas comparar POM con Screenplay y decir cuál usarías en cada caso.

## Requisitos

- Node.js 24 LTS (trae `npm`, el instalador de librerías de JavaScript y TypeScript).
- Visual Studio Code con la extensión Playwright Test for VSCode (de Microsoft).
- Git.

El paso a paso para instalar todo en Windows está en `material-de-apoyo/Prerrequisitos_Instalacion_Entorno_Windows.docx`.

Versiones del proyecto: `@playwright/test` 1.63.0, TypeScript 6.0.3. Probado con Node.js 24.19.0 y npm 11.17.0.

## Cómo se ejecuta en Windows

Abre PowerShell y sigue estos pasos.

1. Clona el repositorio (si no lo tienes) y cámbiate a esta rama:

   ```powershell
   git clone https://github.com/dzapbeta/semillero2026.git
   cd semillero2026
   git switch 04-playwright-pom
   ```

2. Instala las librerías del proyecto. `npm ci` lee `package-lock.json` y baja exactamente las mismas versiones que usa el profesor. Crea la carpeta `node_modules`:

   ```powershell
   npm ci
   ```

3. Instala el navegador de Playwright. Se hace una sola vez por equipo y descarga varios cientos de megas, así que hazlo antes de la clase:

   ```powershell
   npm run instalar:navegador
   ```

   Este comando hace lo mismo que `npx playwright install chromium`, pero ya viene preparado para la red de la empresa (usa los certificados de Windows). Debe mostrar líneas que dicen `downloaded to ...ms-playwright\chromium-...`. Si ya estaba instalado, termina rápido sin descargar nada.

4. Corre las pruebas:

   ```powershell
   npm test
   ```

   Debes ver algo así:

   ```
   Running 5 tests using 1 worker

     ✓  1 [chromium] › tests/login.spec.ts:11:7 › Inicio de sesión en SauceDemo › login exitoso con usuario estándar (2.8s)
     ✓  2 [chromium] › tests/login.spec.ts:21:7 › Inicio de sesión en SauceDemo › login con usuario bloqueado muestra mensaje de error (1.0s)
     ✓  3 [chromium] › tests/login.spec.ts:32:9 › Inicio de sesión en SauceDemo › login inválido: clave incorrecta (953ms)
     ✓  4 [chromium] › tests/login.spec.ts:32:9 › Inicio de sesión en SauceDemo › login inválido: usuario vacío (897ms)
     ✓  5 [chromium] › tests/login.spec.ts:32:9 › Inicio de sesión en SauceDemo › login inválido: clave vacía (925ms)

     5 passed (8.7s)
   ```

Otros comandos útiles:

| Qué quieres hacer | Comando |
|---|---|
| Ver el navegador mientras corren las pruebas | `npm run test:visible` |
| Abrir el modo visual de Playwright (lista de pruebas, línea de tiempo) | `npm run test:ui` |
| Abrir el reporte HTML de la última ejecución | `npm run reporte` |
| Correr un solo archivo | `npx playwright test tests/login.spec.ts` |
| Correr las pruebas cuyo nombre contenga un texto | `npx playwright test -g "usuario bloqueado"` |
| Revisar que el código TypeScript no tenga errores de tipos | `npm run revisar:tipos` |

Todos los scripts están en `package.json`, en la sección `scripts`. Por defecto las pruebas corren sin ventana (headless), que es más rápido.

## Cómo correr las pruebas desde VS Code

1. Abre la carpeta del proyecto en VS Code (Archivo, Abrir carpeta). Si VS Code te sugiere instalar la extensión recomendada, acepta: el proyecto la pide en `.vscode/extensions.json`.
2. Abre `tests/login.spec.ts`. Junto a cada `test` aparece un triángulo verde: haz clic y corre solo esa prueba. Clic derecho sobre el triángulo y "Debug Test" la corre en modo depuración, parando en los puntos de interrupción que marques.
3. En la barra de la izquierda abre la pestaña Testing (el ícono del matraz). Ahí ves todas las pruebas en árbol y puedes correr todas, un archivo o una sola.
4. En la misma pestaña, en la sección de Playwright:
   - Show browser abre el navegador visible mientras corre la prueba.
   - Pick locator te deja pasar el mouse sobre la página y te propone el localizador de ese elemento. Sirve mucho para la actividad.

## Cómo funciona

### Playwright en pocas palabras

Tú le dices a Playwright "escribe esto en el campo Username" o "revisa que el título diga Products" y él lo hace en un navegador de verdad. Dos cosas que te van a ahorrar dolores de cabeza:

- Antes de hacer clic o escribir, Playwright espera solo a que el elemento aparezca.
- `await expect(localizador).toHaveText('Products')` vuelve a mirar la página hasta 5 segundos antes de fallar. A eso le llaman aserción web-first. Por eso no hay esperas escritas a mano.

Si una prueba falla, Playwright guarda una captura de pantalla y una traza (una grabación paso a paso) en `test-results`, y las muestra dentro del reporte HTML.

### async y await

El navegador tarda en hacer cada cosa. `await` quiere decir "espera a que esto termine antes de seguir con la siguiente línea", como cuando haces fila en el banco: no pasas a la siguiente ventanilla hasta que te atienden en la primera. Toda función que usa `await` se marca con `async`. Pon `await` delante de cada acción y de cada `expect`: si se te olvida, la prueba no espera y deja de ser confiable (al preparar esta clase, quitar un `await` hizo que una prueba siguiera pasando sin avisar nada).

### POM: un control remoto por pantalla

Piensa en el televisor de tu casa. Para cambiar de canal no abres el televisor: usas el control remoto, que tiene botones con nombres claros y una pantallita donde ves el canal. POM es lo mismo. Cada pantalla de la aplicación tiene su clase, y esa clase es su control remoto:

- Los localizadores que solo sirven para actuar (campos, botones) son `private readonly`: son los cables de adentro, la prueba no los ve.
- Los métodos son los botones: `abrir()` e `iniciarSesion(usuario, clave)`.
- Lo que la prueba necesita revisar (el mensaje de error, el título) es `readonly` público: es la pantallita. La prueba lo mira, pero no lo puede cambiar.

Si mañana cambia el HTML del botón Login, lo corriges en `pages/LoginPage.ts` y ninguna prueba se toca.

Así se ve una prueba:

```ts
test('login con usuario bloqueado muestra mensaje de error', async ({ page }) => {
  const login = new LoginPage(page);
  await login.abrir();
  await login.iniciarSesion(usuarioBloqueado.nombre, usuarioBloqueado.clave);

  await expect(login.mensajeError).toHaveText('Epic sadface: Sorry, this user has been locked out.');
});
```

Playwright le entrega a cada prueba una pestaña nueva y limpia (`page`), y la cierra al terminar. Por eso no necesitamos una clase que abra y cierre el navegador.

No usamos una clase padre para las páginas (una `PaginaBase`). Con dos páginas pequeñas lo único que compartirían es guardar `page`, y una clase más sería un archivo más que leer sin ganar nada.

### Data driven con un for

Los casos de login inválido están en `data/usuarios.ts`, en una lista. En `tests/login.spec.ts` un `for` recorre la lista y crea una prueba por cada caso. Por eso 3 pruebas escritas dan 5 resultados. Para agregar un caso nuevo solo agregas un elemento a la lista.

### De Java a TypeScript

| En Java | En TypeScript |
|---|---|
| `String nombre = "Ana";` | `const nombre: string = 'Ana';` (el tipo va después de los dos puntos) |
| `private final Locator boton;` | `private readonly boton: Locator;` |
| `public LoginPage(Page page)` | `constructor(page: Page)` |
| `import co.com.semillero.pages.LoginPage;` | `import { LoginPage } from '../pages/LoginPage';` |
| Todo lo `public` se puede usar desde otra clase | Solo lo que tiene `export` se puede importar desde otro archivo |
| `@Test void loginExitoso() { ... }` | `test('login exitoso', async ({ page }) => { ... });` |

## Estructura de carpetas

```
04-playwright-pom/
├── package.json            nombre del proyecto, scripts (npm test...) y librerías
├── package-lock.json       versiones exactas de las librerías; lo usa npm ci
├── playwright.config.ts    configuración de Playwright: dirección de la página, reportes, capturas y trazas
├── tsconfig.json           configuración de TypeScript para revisar tipos
├── .vscode/
│   └── extensions.json     recomienda la extensión de Playwright para VS Code
├── pages/                  los Page Objects: un control remoto por pantalla
│   ├── LoginPage.ts
│   └── ProductosPage.ts
├── data/
│   └── usuarios.ts         datos de prueba: usuarios y casos de login inválido
├── tests/
│   └── login.spec.ts       las pruebas: 2 normales y 1 que se repite con los datos
├── material-de-apoyo/      guías, presentación y actividad
└── README.md               este archivo
```

Estas carpetas no se suben a Git y se crean solas: `node_modules` (librerías), `test-results` (capturas y trazas de las pruebas que fallan) y `playwright-report` (reporte HTML).

## POM frente a Screenplay

| | Screenplay (clase 2) | POM (esta clase) |
|---|---|---|
| Quién hace las acciones | Un actor que ejecuta tareas (`tasks/IniciarSesion`) | La página, con sus métodos |
| Dónde están los localizadores | En `userinterfaces/PaginaLogin` | Dentro de cada página |
| Cómo se valida | Con preguntas (`questions/MensajeDeError`) | La prueba revisa un localizador de la página con `expect` |
| Cantidad de clases | Más clases, cada una pequeña | Pocas: una por pantalla |
| Cuándo conviene | Proyectos grandes con muchos flujos que se combinan | Proyectos pequeños o medianos, y para empezar |

No es que uno sea mejor. Cuando las páginas de POM crecen y se llenan de métodos, Screenplay ayuda a partir ese trabajo en piezas más pequeñas.

## Selenium y Serenity (Java) frente a Playwright (TypeScript)

En la clase 2 usaste Serenity, que por dentro maneja el navegador con Selenium y se escribe en Java con Cucumber. Playwright hace el mismo trabajo con otro estilo: espera solo antes de cada acción, trae su propio navegador (no usa el Chrome que tienes instalado), incluye su propio ejecutor de pruebas, reporte HTML y trazas, y su lenguaje principal es TypeScript. No hay uno mejor para todo: en un proyecto real se usa el que ya tenga el equipo.

## Problemas comunes

### `npm` no se reconoce como comando

Node.js no quedó instalado o no está en el PATH. Revisa la guía de prerrequisitos y abre una ventana nueva de PowerShell después de instalar.

### `npm.ps1 no se puede cargar porque la ejecución de scripts está deshabilitada en este sistema`

Es una política de seguridad de PowerShell. Permite los scripts solo para tu usuario y vuelve a intentar:

```powershell
Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned
```

Si no tienes permiso para cambiarla, escribe `npm.cmd` en lugar de `npm` (por ejemplo `npm.cmd test`).

### `unable to get local issuer certificate` o `UNABLE_TO_GET_ISSUER_CERT_LOCALLY` al instalar el navegador

Pasa en redes de empresa con proxy (por ejemplo Zscaler). Usa `npm run instalar:navegador`, que ya pide usar los certificados de Windows. Si instalas por otro camino (por ejemplo `npx playwright install chromium` o desde la extensión de VS Code), define antes la variable en la misma ventana de PowerShell:

```powershell
$env:NODE_OPTIONS="--use-system-ca"
npx playwright install chromium
```

Si igual falla, tu equipo no tiene instalado el certificado del proxy. Pídele ayuda al profesor.

### `browserType.launch: Executable doesn't exist at ...ms-playwright\chromium_headless_shell-...`

No has instalado el navegador. Ejecuta `npm run instalar:navegador`. El mensaje te sugiere `npx playwright install`, que baja tres navegadores; con Chromium nos basta.

### `strict mode violation: locator('input') resolved to 3 elements`

Tu localizador encontró varios elementos y Playwright no adivina cuál quieres. Hazlo más específico, por ejemplo con `getByTestId`, `getByRole` con el nombre, o buscando dentro de un contenedor.

### `expect(locator).toHaveText(expected) failed` con `Error: element(s) not found`

Playwright esperó 5 segundos y nunca encontró el elemento. Casi siempre el localizador está mal escrito. Usa Pick locator en VS Code o Inspeccionar en Chrome para revisarlo.

### `Expected: "Productos"` y `Received: "Products"`

La prueba corrió bien pero el texto no es el esperado. Compara letra por letra: una mayúscula o un punto cuentan.

## Material de apoyo

En la carpeta `material-de-apoyo/`:

- `Prerrequisitos_Instalacion_Entorno_Windows.docx`: cómo instalar el entorno en Windows.
- `Clase4_Guia_Estudio_Playwright_POM.docx`: guía para estudiar antes y después de la clase.
- `Clase4_Presentacion_Playwright_POM.pptx`: presentación de la clase.
- `Clase4_Actividad_Playwright_POM.docx`: actividad, rúbrica y forma de entrega.

Documentación oficial: [Playwright con TypeScript](https://playwright.dev/docs/intro), [Page Object Models](https://playwright.dev/docs/pom) y [extensión de VS Code](https://playwright.dev/docs/getting-started-vscode).
