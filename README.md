# Semillero QA 2026 · Automatización Web

Este es el repositorio del módulo de Automatización Web (QA Técnico) del Semillero QA 2026. Lo preparé
para que puedas seguir las clases aunque nunca hayas programado.

La rama `main`, donde estás ahora, solo tiene este índice. Cada clase vive en su propia rama de Git, con
un proyecto pequeño que ya funciona, su propio `README.md` y una carpeta `material-de-apoyo/` con la guía
de estudio, la presentación y la actividad.

Profesor: Diego Zapata (Equipo Transversal QS).

## Clases y ramas

| Clase | Rama | Temas | Fecha y hora de la clase | Entrega de la actividad |
|---|---|---|---|---|
| 1 | `01-fundamentos-java-poo` | Variables, tipos de datos y estructuras de control. POO: clases, objetos y encapsulamiento. Herencia y polimorfismo | Lunes 21 de septiembre de 2026, 9:00 a.m. a 12:00 m. | Miércoles 23 de septiembre de 2026, 8:00 a.m. |
| 2 | `02-screenplay-serenity-cucumber-web` | Frameworks de automatización (Selenium). Screenplay. Capas de la arquitectura. BDD | Miércoles 23 de septiembre de 2026, 9:00 a.m. a 12:00 m. | Viernes 25 de septiembre de 2026, 8:00 a.m. |
| 3 | `03-screenplay-serenity-cucumber-api-datadriven` | Screenplay aplicado a API. BDD (esquema del escenario). Data Driven. Capas | Viernes 25 de septiembre de 2026, 9:00 a.m. a 12:00 m. | Martes 29 de septiembre de 2026, 8:00 a.m. |
| 4 | `04-playwright-pom` | Frameworks de automatización (Playwright con TypeScript). POM (Page Object Model) | Martes 29 de septiembre de 2026, 9:00 a.m. a 12:00 m. | Jueves 1 de octubre de 2026, 8:00 a.m. |

Las clases 2, 3 y 4 empiezan a las 8:00 a.m. con una hora de retroalimentación de la actividad anterior.
La retroalimentación de la actividad 4 es el jueves 1 de octubre de 2026 de 8:00 a 9:00 a.m., y de 9:00 a
10:00 a.m. cerramos el módulo.

## Actividades

Cada actividad se entrega **dos días hábiles** después de su clase, a las 8:00 a.m. (las fechas exactas
están en la tabla). Todas tienen un nivel básico, que es el que se califica sobre 10.0, y un reto opcional.
El reto **no suma a la nota**: lo reviso y lo comento en la retroalimentación, así que hazlo si te queda tiempo
y ganas de practicar.

## Requisitos

Todo se instala en Windows 10 u 11. Las clases 1 a 3 son en Java y la clase 4 es en TypeScript:

- Git (todas las clases)
- Java JDK 21 (Eclipse Temurin), clases 1 a 3
- Gradle 9.7.1 instalado en tu equipo, clases 1 a 3 (los proyectos no traen wrapper, se ejecutan con `gradle`)
- IntelliJ IDEA con los plugins Cucumber for Java y Gherkin, clases 1 a 3
- Google Chrome, clase 2
- Node.js 24 LTS (trae `npm`), clase 4
- Visual Studio Code con la extensión Playwright Test for VSCode (`ms-playwright.playwright`), clase 4

No los instales a ciegas. Sigue la guía paso a paso que está en
`material-de-apoyo/Prerrequisitos_Instalacion_Entorno_Windows.docx`: trae los enlaces oficiales, qué
archivo descargar, qué marcar en cada instalador, cómo comprobar que quedó bien y qué hacer con los
errores más comunes de Windows. Esa guía está en esta rama y en la carpeta `material-de-apoyo/` de todas
las ramas. Hazla antes de la clase 1.

Cuando termines, esto debe funcionar en PowerShell:

```powershell
java -version    # debe decir 21 y Temurin
gradle -v        # debe decir Gradle 9.7.1
git --version
node -v          # debe decir v24
npm -v
code --version
```

## Cómo descargar el repositorio y cambiar de rama

Abre PowerShell y escribe estos comandos uno por uno. Te recomiendo una carpeta corta, sin tildes ni
espacios y fuera de OneDrive, como `C:\semillero`:

```powershell
# 1. Crear la carpeta de trabajo y entrar en ella
mkdir C:\semillero
cd C:\semillero

# 2. Clonar el repositorio (solo la primera vez)
git clone https://github.com/dzapbeta/semillero2026.git
cd semillero2026

# 3. Ver las ramas que hay
git branch -a

# 4. Pasarte a la rama de la clase que vas a trabajar
git switch 01-fundamentos-java-poo

# 5. Ejecutar el proyecto de esa rama (en la rama 01)
gradle run
```

El comando del paso 5 depende de la rama, y el `README.md` de cada una lo explica:

| Rama | Comando |
|---|---|
| `01-fundamentos-java-poo` | `gradle run` (es Java puro, sin pruebas automatizadas) |
| `02-screenplay-serenity-cucumber-web` | `gradle clean test` |
| `03-screenplay-serenity-cucumber-api-datadriven` | `gradle clean test` |
| `04-playwright-pom` | `npm ci` y `npx playwright install chromium` (una sola vez) y luego `npm test` |

Para cambiar a otra clase repites el paso 4 con el nombre de su rama. Si ya tenías el repositorio clonado
y quieres traer lo último que yo haya publicado, entra a la rama y escribe `git pull`.

## Qué hay en cada rama

Las ramas 01, 02 y 03 (Java) tienen la misma forma, así sabes dónde buscar:

```
semillero2026/
├── README.md             qué es el proyecto, cómo se ejecuta y cómo está organizado
├── build.gradle          dependencias y configuración de Gradle
├── settings.gradle       nombre del proyecto
├── src/                  código del proyecto (y sus pruebas desde la clase 2)
└── material-de-apoyo/
    ├── ClaseN_Guia_Estudio_<Tema>.docx               para estudiar antes y después de la clase
    ├── ClaseN_Presentacion_<Tema>.pptx               la presentación de la clase
    ├── ClaseN_Actividad_<Tema>.docx                  la actividad que entregas
    └── Prerrequisitos_Instalacion_Entorno_Windows.docx
```

La rama 04 es un proyecto de Node.js con TypeScript, así que en vez de `build.gradle`, `settings.gradle` y
`src/` tiene esto (el `README.md` y `material-de-apoyo/` son iguales):

```
semillero2026/
├── package.json          librerías del proyecto y el comando npm test
├── package-lock.json     versiones exactas que instala npm ci
├── playwright.config.ts  configuración de Playwright
├── pages/                las páginas del patrón POM
└── tests/                las pruebas
```

Empieza siempre por el `README.md` de la rama: ahí explico cómo correr ese proyecto en Windows y qué
hace cada carpeta.

## Si algo no funciona

Primero revisa el capítulo de problemas frecuentes de la guía de prerrequisitos. Si no lo resuelves,
escríbeme con una captura del error, la salida de `java -version`, `gradle -v`, `git --version` y `node -v`, y qué
estabas haciendo.
