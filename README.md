# Clase 1: Fundamentos de Java y POO

Proyecto de la primera clase del módulo de Automatización Web del Semillero QA 2026. Aquí están los ejemplos
que vemos el lunes 21 de septiembre de 2026 (9:00 a.m. a 12:00 m.): variables, tipos de datos, `if`, `switch`,
ciclos, clases y objetos, encapsulamiento, herencia y polimorfismo. Cada ejemplo tiene pruebas con JUnit 5.

No necesitas saber programar para usarlo. La idea es que lo ejecutes, leas el código (tiene un comentario encima
de cada cosa) y cambies valores para ver qué pasa.

## Requisitos

- JDK 21 (Eclipse Temurin)
- Gradle 9.7.1 instalado (este proyecto no trae `gradlew`, se usa el comando `gradle`)
- Git
- IntelliJ IDEA Community (recomendado)

La instalación paso a paso en Windows está en `Prerrequisitos_Instalacion_Entorno_Windows.docx`, en la carpeta
`material-de-apoyo` de la rama `main` de este repositorio.

Para revisar que todo quedó bien, abre PowerShell y corre:

```powershell
java -version
gradle -v
```

El primero debe mostrar la versión 21 y el segundo `Gradle 9.7.1`.

## Cómo se ejecuta en Windows

1. Abre PowerShell y ve a la carpeta donde guardas tus proyectos, por ejemplo:

   ```powershell
   cd C:\semillero
   ```

2. Clona el repositorio (solo la primera vez) y cámbiate a la rama de esta clase:

   ```powershell
   git clone https://github.com/dzapbeta/semillero2026.git
   cd semillero2026
   git switch 01-fundamentos-java-poo
   ```

3. Corre las pruebas. La primera vez tarda un poco más porque Gradle descarga JUnit:

   ```powershell
   gradle test
   ```

   Vas a ver cada prueba con su nombre y la palabra `PASSED`. Al final debe decir `BUILD SUCCESSFUL`.
   Son 14 pruebas.

4. Corre los ejemplos:

   ```powershell
   gradle run
   ```

   En la consola aparece cada tema con un título, por ejemplo `===== 1. Variables y tipos de datos =====`.

Si quieres ver el reporte de pruebas en el navegador, después de `gradle test` abre
`build\reports\tests\test\index.html`.

## Cómo funciona

`App.java` tiene el método `main`, que es lo primero que Java ejecuta. Desde ahí se llama a cada ejemplo en el
mismo orden de la clase:

1. `Variables` crea un dato de cada tipo (`int`, `double`, `boolean`, `char`, `String`), hace una suma y pega dos textos.
2. `Condicionales` decide si una nota aprueba (con `if/else`) y qué hacer según el color del semáforo (con `switch`).
3. `Ciclos` cuenta de 1 a 5 con `for`, simula tres intentos de login con `while` y saluda a una lista de nombres con `for-each`.
4. `Estudiante` muestra una clase y dos objetos creados con `new`.
5. `CuentaBancaria` guarda el saldo como privado. Solo cambia con `depositar()` y `retirar()`, y nunca queda negativo.
6. `Animal` es la clase padre. `Perro` y `Gato` heredan de ella y cada uno cambia `hacerSonido()`. En `App`
   se recorre una lista de `Animal` y cada objeto responde con su propio sonido: eso es polimorfismo.

Las pruebas están en `src/test` y revisan `Condicionales`, `CuentaBancaria` y los animales. Cada una tiene un
`@DisplayName` en español que dice qué revisa.

## Estructura de carpetas

```
01-fundamentos-java-poo/
├── build.gradle            configuración de Gradle: plugins, Java 21, JUnit 5 y clase principal
├── settings.gradle         nombre del proyecto
├── material-de-apoyo/      guía de estudio, presentación y actividad de la clase
└── src/
    ├── main/java/co/com/semillero/
    │   ├── App.java                    ejecuta todos los ejemplos en orden
    │   ├── variables/Variables.java    tipos de datos
    │   ├── control/Condicionales.java  if/else y switch
    │   ├── control/Ciclos.java         for, while y for-each
    │   ├── poo/Estudiante.java         clase, objeto y constructor
    │   ├── poo/CuentaBancaria.java     encapsulamiento
    │   └── herencia/                   Animal (padre), Perro y Gato (hijas)
    └── test/java/co/com/semillero/
        ├── control/CondicionalesTest.java
        ├── poo/CuentaBancariaTest.java
        └── herencia/AnimalTest.java
```

`src/main` es el código del programa y `src/test` son las pruebas. Gradle crea una carpeta `build` cuando
compilas; no se sube a Git.

## Problemas comunes

**PowerShell dice que `gradle` no se reconoce.** La carpeta `bin` de Gradle no está en la variable `Path`.
Agrégala, cierra PowerShell y ábrelo otra vez. Una consola que ya estaba abierta no ve el cambio.

**`ERROR: JAVA_HOME is set to an invalid directory`.** `JAVA_HOME` debe apuntar a la carpeta del JDK 21, no a
su carpeta `bin`. Corrígela, reinicia PowerShell y prueba con `echo $env:JAVA_HOME`.

**`Directory '...' does not contain a Gradle build.`** Estás en otra carpeta. Entra a la carpeta donde está
`build.gradle` con `cd`.

**`java -version` muestra otra versión (1.8, 17).** Hay otro Java antes en el `Path`. Deja primero el JDK 21.

**Salen símbolos raros en vez de tildes en la consola.** Pasa en algunas consolas de Windows. Por eso los textos
que imprime el proyecto no llevan tildes. En los comentarios y el código sí se pueden usar.

**La primera ejecución falla con `Could not resolve`.** Gradle no pudo descargar JUnit, casi siempre por la red
o el proxy. Intenta desde otra red una vez; después queda guardado en tu equipo.

## Material de apoyo

En la carpeta `material-de-apoyo/`:

- `Clase1_Guia_Estudio_Java_POO.docx`: explica cada tema con el código de este proyecto, qué hacer antes y
  después de clase y preguntas de repaso.
- `Clase1_Presentacion_Java_POO.pptx`: las diapositivas de la clase.
- `Clase1_Actividad_Java_POO.docx`: la actividad. Se entrega el miércoles 23 de septiembre de 2026 a las 8:00 a.m.
