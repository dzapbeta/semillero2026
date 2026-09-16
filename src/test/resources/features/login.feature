# language: es

# Pruebas del servicio de inicio de sesión: POST https://dummyjson.com/auth/login
@login
Característica: Inicio de sesión en la API de DummyJSON
  Como analista QA
  Quiero probar el inicio de sesión con distintos datos
  Para saber que la API responde bien a cada caso

  # Un solo escenario que se repite una vez por cada fila de Ejemplos.
  # Los valores entre < > se reemplazan por los de la fila.
  Esquema del escenario: Iniciar sesión con <caso>
    Cuando el analista inicia sesión con el usuario "<usuario>" y la clave "<clave>"
    Entonces el código de respuesta debe ser <codigo>
    Y el campo "<campo>" de la respuesta debe ser "<valor>"

    # Cada fila es una prueba distinta: los datos que se envían y lo que esperamos de vuelta.
    Ejemplos:
      | caso             | usuario | clave      | codigo | campo    | valor                          |
      | datos correctos  | emilys  | emilyspass | 200    | username | emilys                         |
      | clave incorrecta | emilys  | clave123   | 400    | message  | Invalid credentials            |
      | campos en blanco |         |            | 400    | message  | Username and password required |
