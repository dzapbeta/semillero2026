# Semillero QA 2026 · Módulo Automatización Web

Repositorio de clase del módulo **Automatización Web (QA Técnico)**. Cada clase vive en su
propia **rama de Git**: allí está el proyecto base ya automatizado y funcionando, su `README`
(cómo se ejecuta, cómo funciona y arquitectura de carpetas) y la carpeta
`material-de-apoyo/` con la guía de estudio (Word), la presentación (PowerPoint) y la
actividad a entregar (Word).

> Esta rama `main` es solo el índice. El código está en las ramas de cada clase.

## Clases y ramas

| Clase | Rama | Temas | Clase | Entrega de la actividad |
|---|---|---|---|---|
| 1 | [`01-fundamentos-java-poo`](../../tree/01-fundamentos-java-poo) | Variables, tipos de datos y estructuras de control · POO: clases, objetos y encapsulamiento · Herencia y polimorfismo | Lun 21-sep-2026 · 9:00–12:00 | Mié 23-sep · 8:00 a.m. |
| 2 | [`02-screenplay-serenity-cucumber-web`](../../tree/02-screenplay-serenity-cucumber-web) | Frameworks (Selenium) · Screenplay · Capas de la arquitectura · BDD | Mié 23-sep-2026 · 8:00–12:00 | Vie 25-sep · 8:00 a.m. |
| 3 | [`03-screenplay-serenity-cucumber-api-datadriven`](../../tree/03-screenplay-serenity-cucumber-api-datadriven) | Screenplay para API · BDD (esquema del escenario) · Data Driven · Capas | Vie 25-sep-2026 · 8:00–12:00 | Mar 29-sep · 8:00 a.m. |
| 4 | [`04-playwright-pom`](../../tree/04-playwright-pom) | Frameworks (Playwright) · POM (Page Object Model) | Mar 29-sep-2026 · 8:00–12:00 | Jue 1-oct · 8:00 a.m. |
| Cierre | — | Retroalimentación de la actividad 4 y cierre del módulo | Jue 1-oct-2026 · 8:00–10:00 | — |

Las sesiones de 8:00 a 9:00 de las clases 2, 3 y 4 son la **retroalimentación de la
actividad anterior**. Las actividades se entregan **dos días hábiles** después de cada clase.

## Requisitos (para todas las ramas)

| Herramienta | Versión mínima | Cómo comprobarlo |
|---|---|---|
| JDK | 17 (recomendado 21) | `java -version` |
| Maven | 3.9 | `mvn -v` |
| Git | 2.40 | `git --version` |
| Google Chrome | última estable | Ramas 02 y 04 |
| IDE | IntelliJ IDEA Community (recomendado) con los plugins *Cucumber for Java* y *Gherkin* | — |

## Cómo trabajar con las ramas

```bash
# 1. Clonar el repositorio (una sola vez)
git clone https://github.com/dzapbeta/semillero2026.git
cd semillero2026

# 2. Ver las ramas disponibles
git branch -a

# 3. Cambiarse a la rama de la clase
git switch 01-fundamentos-java-poo

# 4. Seguir el README de esa rama
```

## Estructura común de cada rama

```
<rama>/
├── README.md               ← cómo se ejecuta, cómo funciona y arquitectura de carpetas
├── pom.xml                 ← dependencias y plugins de Maven
├── src/                    ← proyecto base automatizado
└── material-de-apoyo/
    ├── ClaseN_Guia_Estudio_*.docx    ← para estudiar antes y después de la clase
    ├── ClaseN_Presentacion_*.pptx    ← presentación editable de la clase
    └── ClaseN_Actividad_*.docx       ← actividad a entregar en dos días
```
