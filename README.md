# Clase 1: Fundamentos de Java y POO

Proyecto de la primera clase del módulo de Automatización Web del Semillero QA 2026. Aquí están los ejemplos
que vemos el lunes 21 de septiembre de 2026 (9:00 a.m. a 12:00 m.): variables, tipos de datos, `if`, `switch`,
ciclos, clases y objetos, encapsulamiento, herencia y polimorfismo. Todo es Java puro y se ejecuta con un solo
comando: `gradle run`.

No necesitas saber programar para usarlo. La idea es que lo ejecutes, leas el código (tiene un comentario encima
de cada cosa) y cambies valores para ver qué pasa.

## Requisitos

- JDK 21 (Eclipse Temurin)
- Gradle 9.7.1 instalado (este proyecto no trae `gradlew`, se usa el comando `gradle`)
- Git
- IntelliJ IDEA (recomendado)

La instalación paso a paso en Windows está en `material-de-apoyo/Prerrequisitos_Instalacion_Entorno_Windows.docx`.

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

3. Ejecuta el proyecto:

   ```powershell
   gradle run
   ```

   En la consola aparece cada tema con un título, por ejemplo `===== 1. Variables y tipos de datos =====`,
   y debajo lo que imprime ese ejemplo. Al final debe decir `BUILD SUCCESSFUL`.

Ese es el único comando que necesitas en esta clase. La consola es donde compruebas que todo funciona: si
cambias un valor en el código y vuelves a correr `gradle run`, ves el cambio ahí mismo.

## Cómo funciona

`App.java` tiene el método `main`, que es lo primero que Java ejecuta. Desde ahí se llama a cada ejemplo en el
mismo orden de la clase:

1. `Variables` crea un dato de cada tipo (`int`, `double`, `boolean`, `char`, `String`), hace una suma y pega dos textos.
2. `Condicionales` decide si una nota aprueba (con `if/else`) y qué hacer según el color del semáforo (con `switch`).
   `App` lo usa con tres notas y tres colores, uno que no existe.
3. `Ciclos` cuenta de 1 a 5 con `for`, simula tres intentos de login con `while` y saluda a una lista de nombres con `for-each`.
4. `Estudiante` muestra una clase y dos objetos creados con `new`.
5. `CuentaBancaria` guarda el saldo como privado. Solo cambia con `depositar()` y `retirar()`, y nunca queda negativo.
   En consola ves cómo se rechazan un depósito negativo y un retiro mayor al saldo.
6. Herencia: `Animal` es la clase padre. `Perro` y `Gato` heredan de ella con `extends`: ya tienen el nombre
   y `getNombre()` sin escribirlos, y cada uno cambia `hacerSonido()` con `@Override`. En consola ves
   `Firulais dice Guau` y `Michi dice Miau`.
7. Interfaces: `Volador` no es una clase, es una lista de obligaciones. `Pajaro` y `Avion` la usan con
   `implements` y cada uno escribe su propio `volar()`. La diferencia con la herencia es esta: con `extends`
   la clase hija recibe código ya hecho del padre (`Perro` recibe el nombre y `getNombre()` de `Animal`) y solo
   puede heredar de una clase; con `implements` la clase no recibe código, recibe la obligación de escribir los
   métodos de la interfaz, y puede implementar varias. En consola ves:

   ```
   Gorrion vuela moviendo las alas
   Boeing 737 vuela con sus turbinas
   ```

8. Polimorfismo con métodos sobrecargados: `Calculadora` tiene tres métodos que se llaman igual, `sumar`,
   pero cada uno recibe datos distintos: dos enteros, tres enteros o dos números con decimales. Java escoge cuál
   usar mirando cuántos datos le mandas y de qué tipo son. En consola ves:

   ```
   sumar(2, 3) = 5
   sumar(2, 3, 4) = 9
   sumar(2.5, 1.5) = 4.0
   ```

   No lo confundas con lo que hace `Perro`: sobrescribir (`@Override`) es cambiar un método que viene del padre;
   sobrecargar es tener varios métodos con el mismo nombre y datos distintos en la misma clase.

## Cómo leer un error de Java

Si escribes algo mal, Java no compila, no se ejecuta ningún ejemplo y la consola dice `BUILD FAILED`. Arriba de
eso está el mensaje que importa. Por ejemplo, si borras el punto y coma de `int edad = 20;` en `Variables.java`:

```
> Task :compileJava FAILED
...\variables\Variables.java:9: error: ';' expected
        int edad = 20
                     ^
1 error
```

Léelo así: primero el archivo y la línea (`Variables.java:9`), después qué pasó (`';' expected`, faltó un punto
y coma) y abajo la línea con un `^` que señala el sitio exacto.

Otro error que vas a ver en clase aparece si en `App.java`, debajo de `cuenta.depositar(100000);`, escribes
`cuenta.saldo = -500;`:

```
> Task :compileJava FAILED
...\App.java:71: error: saldo has private access in CuentaBancaria
        cuenta.saldo = -500;
              ^
1 error
```

Dice que `saldo` es privado dentro de `CuentaBancaria`. Es el encapsulamiento haciendo su trabajo: el saldo solo
se cambia con `depositar()` o `retirar()`.

## Estructura de carpetas

```
01-fundamentos-java-poo/
├── build.gradle            configuración de Gradle: plugins, Java 21 y clase principal
├── settings.gradle         nombre del proyecto
├── material-de-apoyo/      prerrequisitos, guía de estudio, presentación y actividad
└── src/
    └── main/java/co/com/semillero/
        ├── App.java                    ejecuta todos los ejemplos en orden
        ├── variables/Variables.java    tipos de datos
        ├── control/Condicionales.java  if/else y switch
        ├── control/Ciclos.java         for, while y for-each
        ├── poo/Estudiante.java         clase, objeto y constructor
        ├── poo/CuentaBancaria.java     encapsulamiento
        ├── herencia/                   Animal (padre), Perro y Gato (hijas)
        ├── interfaces/                 Volador (interfaz), Pajaro y Avion (implements)
        └── polimorfismo/Calculadora.java  tres métodos sumar (sobrecarga)
```

Cada carpeta dentro de `semillero` es un paquete, una forma de agrupar clases del mismo tema. Gradle crea una
carpeta `build` cuando compilas; no se sube a Git.

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

## Material de apoyo

En la carpeta `material-de-apoyo/`:

- `Prerrequisitos_Instalacion_Entorno_Windows.docx`: cómo instalar Java, Gradle, Git e IntelliJ en Windows.
- `Clase1_Guia_Estudio_Java_POO.docx`: explica cada tema con el código de este proyecto, qué hacer antes y
  después de clase y preguntas de repaso.
- `Clase1_Presentacion_Java_POO.pptx`: las diapositivas de la clase.
- `Clase1_Actividad_Java_POO.docx`: la actividad. Se entrega el miércoles 23 de septiembre de 2026 a las 8:00 a.m.
