# Clase 1 · Fundamentos de Java y POO para automatización

Semillero QA 2026 · Módulo **Automatización Web (QA Técnico)** · Rama `01-fundamentos-java-poo`

| | |
|---|---|
| **Clase** | Lunes 21 de septiembre de 2026 · 9:00 a.m. – 12:00 m. |
| **Profesor** | Diego Zapata — Equipo Transversal QS |
| **Entrega de la actividad** | Miércoles 23 de septiembre de 2026, 8:00 a.m. |
| **Retroalimentación** | Miércoles 23 de septiembre de 2026, 8:00 – 9:00 a.m. |

← [Volver al índice del módulo (rama `main`)](../../tree/main)

---

## Objetivo de la clase

Que cada semillero **lea, ejecute y modifique** un proyecto Java con Maven, entendiendo las bases
que usa todo framework de automatización: variables y control de flujo, clases y objetos,
encapsulamiento, herencia y polimorfismo. Todos los ejemplos usan el lenguaje del día a día de QA
(casos de prueba, defectos, navegadores, ejecuciones), y el proyecto trae sus propias pruebas
JUnit 5 + AssertJ para aprender también a **probar** el código.

## Temas

1. **Variables, tipos de datos y estructuras de control** — primitivos, wrappers, `String`, `var`, constantes `final`, casting, operadores, `if/else`, `switch`, `for`, `while`, `do-while`, `break/continue`, arreglos, `List`, `Map` y `Set`.
2. **POO: clases, objetos y encapsulamiento** — atributos privados, constructores con validación, getters, `toString`, `enum`.
3. **Herencia y polimorfismo** — clase abstracta, `extends`, `super`, `@Override`, `protected`, interfaces, sobrecarga vs. sobrescritura.
4. **Puente a automatización** — un Page Object simulado (`PaginaBase` → `PaginaLogin`), base de POM y Screenplay.

## Requisitos

| Herramienta | Versión | Cómo comprobarlo |
|---|---|---|
| JDK | 17 o superior (probado con Temurin 21) | `java -version` |
| Maven | 3.9 o superior (probado con 3.9.16) | `mvn -v` |
| Git | 2.40 o superior | `git --version` |
| IDE | IntelliJ IDEA Community (recomendado) | — |

No se necesita navegador: en esta clase todo es Java puro (el navegador y la página son **simulados**).

Versiones que declara el `pom.xml`: Java `release` 17 · JUnit Jupiter 5.14.4 · AssertJ 3.27.7 ·
maven-compiler-plugin 3.16.0 · maven-surefire-plugin 3.6.0 · exec-maven-plugin 3.6.4.

## Cómo ejecutar

Todos los comandos se ejecutan en la carpeta raíz del proyecto (donde está `pom.xml`).

```bash
# 1. Traer la rama (si ya clonaste el repositorio)
git switch 01-fundamentos-java-poo

# 2. Compilar y ejecutar TODAS las pruebas (debe terminar en BUILD SUCCESS)
mvn clean verify

# 3. Ejecutar solo una clase de pruebas
mvn test -Dtest=CasoDePruebaTest

# 4. Ejecutar un solo método de prueba (en PowerShell, deja las comillas)
mvn test "-Dtest=CasoDePruebaTest#casoBloqueadoNoSeEjecuta"

# 5. Ejecutar la demostración por consola (método main de App)
mvn -q compile exec:java
```

Resultado esperado del paso 2 (resumen real):

```
[INFO] Tests run: 58, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Desde IntelliJ: *File → Open* → seleccionar el `pom.xml` → *Open as Project*. Luego clic derecho
sobre `src/test/java` → *Run 'All Tests'*, o sobre `App.java` → *Run 'App.main()'*.

## Cómo funciona (orden sugerido para recorrerlo en clase)

Cada paquete de `src/main/java` tiene su espejo en `src/test/java`. La recomendación es leer
primero la clase, luego su prueba, y por último ver su sección en la demo (`App`).

| # | Paquete / clase | Qué demuestra | Prueba que lo respalda |
|---|---|---|---|
| 1 | `variables.TiposDeDatos` | Primitivos, `var`, constantes `final`, casting implícito y explícito, métodos de `String`, `Integer.valueOf`, `equals` vs `==` | `TiposDeDatosTest` |
| 2 | `variables.EstructurasDeControl` | `if/else` (severidad), `switch` expression, `for`, `for-each`, `break`, `continue`, `while` (reintentos), `do-while` (espera de carga) | `EstructurasDeControlTest` (incluye una prueba parametrizada) |
| 3 | `variables.Colecciones` | Arreglo, `List` (filtrar casos), `Map` (conteo por estado), `Set` (defectos únicos) | `ColeccionesTest` |
| 4 | `poo.encapsulamiento.CasoDePrueba`, `Defecto`, `EstadoCaso`, `Severidad` | Clase vs. objeto, atributos `private`, constructor que valida, getters, lista de solo lectura, `toString`, `enum` con atributos | `CasoDePruebaTest`, `DefectoTest` |
| 5 | `poo.herencia.Navegador` → `Chrome`, `Firefox`, `Edge` | Clase `abstract`, método abstracto, `extends`, `super(...)`, `super.metodo()`, `@Override`, `protected` | `NavegadorTest` |
| 6 | `poo.polimorfismo.Ejecutable` → `PruebaWeb`, `PruebaApi`, `PruebaMovil`; `EjecutorDePruebas`, `ResumenEjecucion` | Interfaz como contrato, método `default`, **polimorfismo dinámico** (una `List<Ejecutable>` con tipos distintos) | `EjecutorDePruebasTest` |
| 7 | `poo.polimorfismo.Reportero` | **Polimorfismo estático**: sobrecarga de `formatear(...)` | `ReporteroTest` |
| 8 | `paginas.PaginaBase` → `PaginaLogin` | Page Object simulado: localizadores privados, acciones heredadas, métodos de negocio | `PaginaLoginTest` |
| 9 | `App` | Ejecuta todo lo anterior en orden e imprime en consola | `AppTest` (prueba de humo) |

Cada clase, interfaz, enum, atributo, constructor, método y prueba tiene encima un comentario
Javadoc en español que explica para qué es y qué concepto ilustra.

## Arquitectura de carpetas

```
01-fundamentos-java-poo/
├── README.md                                   ← este archivo
├── pom.xml                                     ← dependencias (JUnit, AssertJ) y plugins (compiler, surefire, exec), comentado
├── .gitignore                                  ← evita subir target/, archivos del IDE, logs
├── material-de-apoyo/
│   ├── Clase1_Guia_Estudio_Java_POO.docx       ← guía para estudiar antes y después de la clase
│   ├── Clase1_Presentacion_Java_POO.pptx       ← presentación de la clase (editable)
│   └── Clase1_Actividad_Java_POO.docx          ← actividad a entregar y rúbrica
└── src/
    ├── main/java/co/com/semillero/fundamentos/
    │   ├── App.java                            ← main: demo por consola en el orden de la clase
    │   ├── variables/                          ← TEMA 1
    │   │   ├── TiposDeDatos.java               ← tipos, casting, String, constantes
    │   │   ├── EstructurasDeControl.java       ← if, switch, for, while, do-while, break, continue
    │   │   └── Colecciones.java                ← arreglos, List, Map, Set
    │   ├── poo/
    │   │   ├── encapsulamiento/                ← TEMA 2
    │   │   │   ├── CasoDePrueba.java           ← clase con atributos privados y validaciones
    │   │   │   ├── Defecto.java                ← asociación con CasoDePrueba
    │   │   │   ├── EstadoCaso.java             ← enum: PENDIENTE, EXITOSO, FALLIDO, BLOQUEADO
    │   │   │   └── Severidad.java              ← enum con atributo nivel
    │   │   ├── herencia/                       ← TEMA 3
    │   │   │   ├── Navegador.java              ← clase abstracta (padre)
    │   │   │   ├── Chrome.java                 ← hija: agrega modo incógnito
    │   │   │   ├── Firefox.java                ← hija: sobrescribe abrir() con super
    │   │   │   └── Edge.java                   ← hija: sobrescribe la validación protegida
    │   │   └── polimorfismo/                   ← TEMA 3
    │   │       ├── Ejecutable.java             ← interfaz (contrato)
    │   │       ├── PruebaWeb.java              ← implementación web (usa un Navegador)
    │   │       ├── PruebaApi.java              ← implementación API
    │   │       ├── PruebaMovil.java            ← implementación móvil
    │   │       ├── EjecutorDePruebas.java      ← recorre List<Ejecutable>: polimorfismo dinámico
    │   │       ├── ResumenEjecucion.java       ← resultado inmutable de la ejecución
    │   │       └── Reportero.java              ← sobrecarga de formatear(...)
    │   └── paginas/                            ← PUENTE A AUTOMATIZACIÓN
    │       ├── PaginaBase.java                 ← clase base de páginas (POM)
    │       └── PaginaLogin.java                ← Page Object simulado
    └── test/java/co/com/semillero/fundamentos/ ← espejo de main: una o más pruebas por paquete
        ├── AppTest.java
        ├── variables/        (TiposDeDatosTest, EstructurasDeControlTest, ColeccionesTest)
        ├── poo/encapsulamiento/ (CasoDePruebaTest, DefectoTest)
        ├── poo/herencia/     (NavegadorTest)
        ├── poo/polimorfismo/ (EjecutorDePruebasTest, ReporteroTest)
        └── paginas/          (PaginaLoginTest)
```

`target/` se crea al compilar (clases, `.jar` y reportes) y **no** se sube al repositorio.

## Cómo leer el reporte de pruebas (`target/surefire-reports`)

Después de `mvn clean verify` o `mvn test`, surefire deja **dos archivos por clase de prueba**:

| Archivo | Para qué sirve |
|---|---|
| `co.com.semillero.fundamentos.<paquete>.<Clase>Test.txt` | Resumen legible: cuántas pruebas corrieron, fallaron o dieron error, y el detalle de cada falla |
| `TEST-co.com.semillero.fundamentos.<paquete>.<Clase>Test.xml` | Mismo resultado en XML (lo leen Jenkins, GitHub Actions, Azure DevOps…) con un `<testcase>` por método |

Cómo interpretar los contadores:

- **Tests run**: pruebas ejecutadas.
- **Failures**: una aserción no se cumplió (el código hace algo distinto a lo esperado).
- **Errors**: la prueba explotó con una excepción no esperada (por ejemplo `NullPointerException`).
- **Skipped**: pruebas omitidas (por ejemplo con `@Disabled`).

Ejemplo **real** de una falla (se cambió a propósito el valor esperado `"Pasó"` por `"Paso"`):

```
Tests run: 12, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.081 s <<< FAILURE! -- in co.com.semillero.fundamentos.variables.EstructurasDeControlTest
co.com.semillero.fundamentos.variables.EstructurasDeControlTest.describeResultado -- Time elapsed: 0.004 s <<< FAILURE!
org.opentest4j.AssertionFailedError:

expected: "Paso"
 but was: "Pasó"
	at co.com.semillero.fundamentos.variables.EstructurasDeControlTest.describeResultado(EstructurasDeControlTest.java:44)
```

Se lee así: **qué prueba** falló (`describeResultado`), **qué se esperaba** (`expected`), **qué llegó**
(`but was`) y **en qué línea** está la aserción (`EstructurasDeControlTest.java:44`).

## Errores comunes y soluciones

| Síntoma | Causa probable | Solución |
|---|---|---|
| `mvn: command not found` / `"mvn" no se reconoce` | Maven no está instalado o no está en el `PATH` | Instalar Maven 3.9+ y agregar su carpeta `bin` al `PATH`; abrir una terminal nueva y probar `mvn -v` |
| `error: release version 17 not supported` | Se está usando un JDK anterior a 17 | Instalar JDK 17 o 21 y revisar que `mvn -v` muestre esa versión (`JAVA_HOME`) |
| `No tests matching pattern "..." were executed!` | El nombre en `-Dtest=` no coincide con ninguna clase | Usar el nombre exacto de la clase, sin `.java`: `-Dtest=CasoDePruebaTest` |
| `BUILD FAILURE` con `There are test failures` | Alguna prueba falló | Abrir el `.txt` de esa clase en `target/surefire-reports` y leer `expected` / `but was` |
| `cannot find symbol` al compilar | Nombre mal escrito, falta un `import` o el paquete no coincide con la carpeta | Revisar mayúsculas/minúsculas, el `import` y que la línea `package` coincida con la ruta de carpetas |
| `Navegador is abstract; cannot be instantiated` | Se intentó `new Navegador(...)` | Crear un hijo concreto: `new Chrome(false, false)` |
| Tildes o `ñ` salen como `Ã³` en la consola de Windows | La consola no usa UTF-8 | Ejecutar `chcp 65001` antes de `mvn`, o usar la terminal de IntelliJ |
| IntelliJ marca todo en rojo | El proyecto se abrió como carpeta y no como proyecto Maven | *File → Open* sobre `pom.xml` → *Open as Project*, luego *Reload All Maven Projects* |
| `==` entre dos `String` da `false` aunque "se vean iguales" | `==` compara referencias, no contenido | Usar `texto1.equals(texto2)` |

## Material de apoyo

| Archivo | Uso |
|---|---|
| [Guía de estudio (Word)](material-de-apoyo/Clase1_Guia_Estudio_Java_POO.docx) | Teoría desde cero con ejemplos del proyecto, tablas comparativas, glosario y autoevaluación |
| [Presentación (PowerPoint)](material-de-apoyo/Clase1_Presentacion_Java_POO.pptx) | Diapositivas de la clase con notas del orador |
| [Actividad (Word)](material-de-apoyo/Clase1_Actividad_Java_POO.docx) | Enunciado, entregables, rúbrica y checklist |

## Actividad y fecha de entrega

Extender este proyecto con una nueva jerarquía de clases, validaciones y **pruebas JUnit propias**
(nivel básico obligatorio + reto opcional). El detalle, la rúbrica (10.0 puntos) y el checklist
están en [`Clase1_Actividad_Java_POO.docx`](material-de-apoyo/Clase1_Actividad_Java_POO.docx).

- **Entrega:** miércoles 23 de septiembre de 2026, 8:00 a.m.
- **Forma:** link a tu repositorio propio en GitHub (o archivo `.zip` del proyecto sin `target/`) **+** evidencia de `mvn clean verify` en verde (captura de pantalla o log con `BUILD SUCCESS`).
- **Retroalimentación:** miércoles 23 de septiembre de 2026, 8:00 – 9:00 a.m.

---

← [Volver al índice del módulo (rama `main`)](../../tree/main) · Repositorio: https://github.com/dzapbeta/semillero2026
